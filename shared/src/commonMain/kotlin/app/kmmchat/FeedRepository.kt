package app.kmmchat

import kotlin.random.Random

class FeedRepository {

    @Throws(Exception::class)
    suspend fun getFeedItems(): List<FeedItem> {
        if (Random.nextBoolean()) {
            throw Exception("Awww...")
        }

        return listOf(
            FeedItem("A", "b"),
            FeedItem("B", "c"),
            FeedItem("C", "a"),
        )
    }

    @Throws(Exception::class)
    suspend fun postToFeed(post: String) {
        if (Random.nextBoolean()) {
            throw Exception("Awww...")
        }
    }
}