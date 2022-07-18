package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.sql.DriverManager.println
import java.time.Duration
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

fun Application.configureSockets() {

    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    routing {
        val connections = Collections.synchronizedSet<Connection?>(LinkedHashSet())
        webSocket("/socket/chat") {
            val thisConnection = Connection(this, call.request.queryParameters["username"] ?: "Anon")
            connections.add(thisConnection)
            try {
                for (frame in incoming) {
                    frame as? Frame.Text ?: continue
                    val receivedText = frame.readText()
                    val receivedMessage: IncomingMessage = Json.decodeFromString(receivedText)
                    connections.forEach {
                        val message = OutgoingMessage(message = receivedMessage.message, from = thisConnection.name)
                        runBlocking {
                            it.session.send(Json.encodeToString(message))
                        }
                    }
                }
            } catch (e: Exception) {
                println(e.localizedMessage)
            } finally {
                println("Removing $thisConnection!")
                connections.remove(thisConnection)
            }
        }
    }
}

@Serializable
data class IncomingMessage(
    val message: String,
    val to: String,
)

@Serializable
data class OutgoingMessage(
    val message: String,
    val from: String,
)


class Connection(val session: DefaultWebSocketSession, username: String) {
    val name = "$username - ${lastId.getAndIncrement()}"

    companion object {
        var lastId = AtomicInteger(0)
    }
}
