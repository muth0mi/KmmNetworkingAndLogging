package app.kmmchat.data

import app.kmmchat.domain.FeedItem
import app.kmmchat.domain.FeedRepository

class FeedRepositoryImpl(
    private val feedApi: FeedApi = FeedApi()
) : FeedRepository {

    @Throws(Exception::class)
    override suspend fun getFeedItems(): List<FeedItem> {
        return feedApi.getFeeds().map { FeedItem(post = it.body, author = it.title) }
    }

    @Throws(Exception::class)
    override suspend fun postToFeed(post: String) {
        return feedApi.addFeed(post, "author 1")
    }
}