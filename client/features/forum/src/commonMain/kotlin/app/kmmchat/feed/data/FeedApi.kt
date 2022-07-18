package app.kmmchat.feed.data

import app.kmmchat.networking.NetworkUtilities
import app.kmmchat.networking.RestNetworkingClient

class FeedApi : RestNetworkingClient() {

    private val baseUrl = "http://" + NetworkUtilities.localHost + ":8080/rest/posts"

    suspend fun getFeeds(): List<FeedDto> {
        return get<List<FeedDto>>(baseUrl + "")
    }

    suspend fun addFeed(post: String, author: String) {
        val request = FeedDto(post = post, author = author)
        post<FeedDto, FeedDto>(baseUrl + "", request)
    }
}
