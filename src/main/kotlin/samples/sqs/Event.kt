package samples.sqs

data class Event(
    val name: String,
    val time: Long,
    val attempt: Int,
)
