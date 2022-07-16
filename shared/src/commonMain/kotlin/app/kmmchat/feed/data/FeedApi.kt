package app.kmmchat.feed.data

import app.kmmchat.Utilities
import app.kmmchat.networking.rest.RestNetworkingClient

class FeedApi : RestNetworkingClient() {

    private val baseUrl = "https://" + Utilities.localHostUrl + ":8080/rest/posts"

    suspend fun getFeeds(): List<FeedDto> {
        return get<List<FeedDto>>(baseUrl + "")
    }

    suspend fun addFeed(post: String, author: String) {
        val request = FeedDto(post = post, author = author)
        post<FeedDto, FeedDto>(baseUrl + "", request)
    }
}
