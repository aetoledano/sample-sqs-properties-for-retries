#!/usr/bin/env bash

echo "Create SQS queues"

MAIN_QUEUE=sample-queue
DLQ_SQS=sample-queue-dlq

awslocal sqs create-queue --queue-name $DLQ_SQS

DLQ_SQS_ARN=$(awslocal sqs get-queue-attributes\
                  --attribute-name QueueArn --queue-url=http://localhost:4566/000000000000/"$DLQ_SQS"\
                  |  sed 's/"QueueArn"/\n"QueueArn"/g' | grep '"QueueArn"' | awk -F '"QueueArn":' '{print $2}' | tr -d '"' | xargs)


echo Arn: $DLQ_SQS_ARN

awslocal sqs create-queue --queue-name $MAIN_QUEUE --attributes '{
                                                                      "RedrivePolicy": "{\"deadLetterTargetArn\":\"'"$DLQ_SQS_ARN"'\",\"maxReceiveCount\":\"3\"}",
                                                                      "DelaySeconds": "5",
                                                                      "VisibilityTimeout": "10"
                                                                      }'
