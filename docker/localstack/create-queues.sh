#!/usr/bin/env bash

echo "Create SQS queues"
awslocal sqs create-queue --queue-name sample-queue --attributes DelaySeconds=10,VisibilityTimeout=10
