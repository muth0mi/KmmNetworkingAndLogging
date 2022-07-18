package com.example.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.util.*

fun Application.configureRestRouting() {
    install(StatusPages) {
        exception<AuthenticationException> { call, cause ->
            call.respond(HttpStatusCode.Unauthorized)
        }

        exception<AuthorizationException> { call, cause ->
            call.respond(HttpStatusCode.Forbidden)
        }
    }

    routing {
        route("/rest/posts") {
            get("") {
                call.respond(posts)
            }
            post("") {
                val post = call.receive<Post>().copy(id = posts.size)
                posts.add(post)
                call.respond(post)
            }
        }
    }
}

class AuthenticationException : RuntimeException()

class AuthorizationException : RuntimeException()

@Serializable
data class Post(
    val id: Int = 0,
    val post: String,
    val author: String,
)

var posts: ArrayList<Post> = ArrayList()