package samples.sqs

import io.awspring.cloud.messaging.core.QueueMessagingTemplate
import org.springframework.stereotype.Component
import samples.sqs.SqsConfig.Companion.QUEUE_NAME

@Component
class SqsPublisher(
    private val messagingTemplate: QueueMessagingTemplate
) {
    
    fun publish() {
        val evt = Event(
            name = "SampleEvent",
            time = System.currentTimeMillis(),
            attempt = 1
        )
        println("Sent ${evt.name}")
        messagingTemplate.convertAndSend(
            QUEUE_NAME,
            evt,
        )
    }
    
}
