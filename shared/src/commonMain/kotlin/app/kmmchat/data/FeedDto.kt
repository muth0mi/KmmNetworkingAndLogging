package app.kmmchat.data

import kotlinx.serialization.Serializable

@Serializable
data class FeedDto(
    val id: Int = 0,
    val title: String,
    val body: String,
)
