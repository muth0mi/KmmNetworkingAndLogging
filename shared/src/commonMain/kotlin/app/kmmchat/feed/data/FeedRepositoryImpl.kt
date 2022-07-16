package app.kmmchat.feed.data

import app.kmmchat.feed.domain.FeedItem
import app.kmmchat.feed.domain.FeedRepository

class FeedRepositoryImpl(
    private val feedApi: FeedApi = FeedApi()
) : FeedRepository {

    @Throws(Exception::class)
    override suspend fun getFeedItems(): List<FeedItem> {
        return feedApi.getFeeds().map { FeedItem(post = it.post, author = it.author) }
    }

    @Throws(Exception::class)
    override suspend fun postToFeed(post: String) {
        return feedApi.addFeed(post, "author 1")
    }
}