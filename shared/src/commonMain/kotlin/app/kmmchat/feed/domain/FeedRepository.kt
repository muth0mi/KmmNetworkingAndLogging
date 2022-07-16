package app.kmmchat.feed.domain

interface FeedRepository {

    @Throws(Exception::class)
    suspend fun getFeedItems(): List<FeedItem>

    @Throws(Exception::class)
    suspend fun postToFeed(post: String)
}