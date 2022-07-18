package app.kmmchat.feed.data

import kotlinx.serialization.Serializable

@Serializable
data class FeedDto(
    val id: Int = 0,
    val post: String,
    val author: String,
)
