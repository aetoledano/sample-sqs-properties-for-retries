package samples.sqs

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import io.awspring.cloud.messaging.config.QueueMessageHandlerFactory
import io.awspring.cloud.messaging.core.QueueMessagingTemplate
import io.awspring.cloud.messaging.support.NotificationMessageArgumentResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.converter.MessageConverter
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver
import java.util.List
import javax.crypto.Cipher.SECRET_KEY


@Configuration
class SqsConfig {
    
    companion object {
        const val QUEUE_NAME = "sample-queue"
    }
    
    @Bean
    fun mappingJackson2MessageConverter(
        objectMapper: ObjectMapper
    ): MappingJackson2MessageConverter? {
        val jackson2MessageConverter = MappingJackson2MessageConverter()
        jackson2MessageConverter.objectMapper = objectMapper
        return jackson2MessageConverter
    }
    
    @Bean
    fun queueMessagingTemplate(
        customAmazonSqs: AmazonSQSAsync
    ): QueueMessagingTemplate? {
        val template = QueueMessagingTemplate(customAmazonSqs)
        
        template.setDefaultDestinationName(QUEUE_NAME)
        
        return template
    }
//
//    @Bean
//    fun endpointConfiguration(): EndpointConfiguration? {
//        return EndpointConfiguration(ENDPOINT, "us-east-1")
//    }
//
//    @Bean
//    @Primary
//    fun amazonSQSAsync(endpointConfiguration: EndpointConfiguration?): AmazonSQSAsync? {
//        val credentials = BasicAWSCredentials("local", "local")
//        return AmazonSQSAsyncClientBuilder
//            .standard()
//            .withEndpointConfiguration(endpointConfiguration)
//            .withCredentials(AWSStaticCredentialsProvider(credentials))
//            .build()
//    }
//
//    @Bean
//    fun queueMessageHandlerFactory(messageConverter: MessageConverter?): QueueMessageHandlerFactory? {
//        val factory = QueueMessageHandlerFactory()
//        factory.setArgumentResolvers(
//            List.of<HandlerMethodArgumentResolver>(
//                NotificationMessageArgumentResolver(
//                    messageConverter
//                )
//            )
//        )
//        return factory
//    }
}
