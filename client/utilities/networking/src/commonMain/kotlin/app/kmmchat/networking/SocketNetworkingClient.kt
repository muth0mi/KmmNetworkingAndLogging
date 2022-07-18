package app.kmmchat.networking

import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.*
import io.ktor.websocket.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

open class SocketNetworkingClient {

    private val client = HttpClient {
        install(WebSockets) {
            contentConverter = KotlinxWebsocketSerializationConverter(Json)
        }
    }

    var socket: WebSocketSession? = null

    suspend fun initSession(baseUrl: String, username: String) {
        println("Initiating socket")

        try {
            socket = client.webSocketSession { url("$baseUrl?username=$username") }
            if (socket?.isActive == true) {
                println("Active socket")
            } else {
                println("Could not create socket connection")
            }
        } catch (e: Exception) {
            println("Socket creation failed: ${e.message}")
            throw  e
        }
    }

    suspend fun closeSession() {
        socket?.close()
    }

    suspend inline fun <reified T : Any> sendData(body: T) {
        try {
            socket?.send(Frame.Text(Json.encodeToString(body)))
        } catch (e: Exception) {
            println("Unable to send message")
            println("Reason: "+e.message)
        }
    }

    suspend inline fun <reified T : Any> receiveData(): Flow<T> {
        return try {
            socket?.incoming
                ?.receiveAsFlow()
                ?.filter { it is Frame.Text }
                ?.map {
                    val json = (it as Frame.Text).readText()
                    Json.decodeFromString<T>(json)
                } ?: flow { }
        } catch (e: Exception) {
            println("Unable to receive message")
            throw e
        }
    }
}
