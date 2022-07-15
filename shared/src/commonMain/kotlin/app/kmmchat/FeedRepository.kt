package app.kmmchat

import kotlin.coroutines.CoroutineContext
import kotlin.random.Random
//import kotlinx.coroutines.delay

class FeedRepository {

    @Throws(Exception::class)
     suspend fun getFeedItems(): List<FeedItem> {
//        delay(3000)
        if (Random.nextBoolean()){
            throw Exception("Awww...")
        }

        return listOf(
            FeedItem("A", "b"),
            FeedItem("B", "c"),
            FeedItem("C", "a"),
        )
    }

    @Throws(Exception::class)
  suspend  fun postToFeed(post: String) {
        //        delay(3000)
        if (Random.nextBoolean()){
            throw Exception("Awww...")
        }
    }
}