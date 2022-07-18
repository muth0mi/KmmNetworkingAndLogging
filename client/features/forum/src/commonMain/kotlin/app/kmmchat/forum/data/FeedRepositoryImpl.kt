package app.kmmchat.forum.data

import app.kmmchat.forum.domain.FeedItem
import app.kmmchat.forum.domain.FeedRepository

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