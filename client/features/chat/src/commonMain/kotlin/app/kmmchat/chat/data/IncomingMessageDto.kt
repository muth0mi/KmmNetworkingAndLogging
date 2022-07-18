package app.kmmchat.chat.data

import kotlinx.serialization.Serializable

@Serializable
data class OutgoingMessageDto(
    val message: String,
    val to: String,
)

@Serializable
data class IncomingMessageDto(
    val message: String,
    val from: String,
)