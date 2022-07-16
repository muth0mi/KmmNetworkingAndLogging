package app.kmmchat.feed.data

import app.kmmchat.networking.rest.RestNetworkingClient

class FeedApi : RestNetworkingClient() {

    private val baseUrl = "https://0e69-105-163-22-159.eu.ngrok.io/rest/posts"

    suspend fun getFeeds(): List<FeedDto> {
        return get<List<FeedDto>>(baseUrl + "")
    }

    suspend fun addFeed(post: String, author: String) {
        val request = FeedDto(post = post, author = author)
        post<FeedDto, FeedDto>(baseUrl + "", request)
    }
}
