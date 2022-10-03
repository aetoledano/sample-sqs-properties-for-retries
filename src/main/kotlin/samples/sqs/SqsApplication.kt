package samples.sqs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener

@SpringBootApplication
class SqsApplication(
    val publisher: SqsPublisher
) {
    
    @EventListener(ApplicationReadyEvent::class)
    fun test() {
        publisher.publish()
    }
    
}

fun main(args: Array<String>) {
    runApplication<SqsApplication>(*args)
}
