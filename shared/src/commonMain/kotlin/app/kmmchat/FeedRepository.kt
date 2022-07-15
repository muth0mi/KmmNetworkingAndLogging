package app.kmmchat

data class FeedItem(
    val post: String,
    val author: String,
)

class FeedRepository {

    fun getFeedItems(): List<FeedItem> {
        return listOf(
            FeedItem("A", "b"),
            FeedItem("B", "c"),
            FeedItem("C", "a"),
        )
    }
}