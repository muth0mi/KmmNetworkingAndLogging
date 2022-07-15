package app.kmmchat

class FeedRepository {

    fun getFeedItems(): List<FeedItem> {
        return listOf(
            FeedItem("A", "b"),
            FeedItem("B", "c"),
            FeedItem("C", "a"),
        )
    }

    fun postToFeed(post:  String) {
    }
}