package app.kmmchat.data

import app.kmmchat.networking.rest.NetworkingClient

class FeedApi : NetworkingClient() {

    private val baseUrl = "https://jsonplaceholder.typicode.com/posts"

    suspend fun getFeeds(): List<FeedDto> {
        return get<List<FeedDto>>(baseUrl + "")
    }

    suspend fun addFeed(post: String, author: String) {
        val request = FeedDto(title = post, body = author)
        post<FeedDto, FeedDto>(baseUrl + "", request)
    }
}
