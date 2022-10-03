package samples.sqs

import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy
import io.awspring.cloud.messaging.listener.annotation.SqsListener
import org.springframework.stereotype.Component
import samples.sqs.SqsConfig.Companion.QUEUE_NAME

@Component
class SqsConsumer {
    
    @SqsListener(
        QUEUE_NAME,
        deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS
    )
    fun consume(evt: Event) {
        val elapsedSeconds = calculateSeconds(evt.time)
        println("" +
            "Received ${evt.name} in ${elapsedSeconds}s. Attempt ${evt.attempt}")
        
        throw Exception()
    }
    
    private fun calculateSeconds(time: Long): Long {
        val millis = System.currentTimeMillis() - time;
        return millis / 1000
    }
}
