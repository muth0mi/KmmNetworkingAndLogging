package app.kmmchat.data

import app.kmmchat.FeedItem
import app.kmmchat.FeedRepository
import kotlin.random.Random

class FeedRepositoryImpl : FeedRepository {

//    override suspend fun getUserWithPhoneNumber(countryCode: Int, phoneNumber: Int): User {
//        val userDto =  feedApi.findUserWithPhoneNumber(countryCode, phoneNumber)
//        return User(id = userDto.id)
//    }

    @Throws(Exception::class)
    override suspend fun getFeedItems(): List<FeedItem> {
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
    override suspend fun postToFeed(post: String) {
        if (Random.nextBoolean()) {
            throw Exception("Awww...")
        }
    }
}