package app.kmmchat.chat.data

import app.kmmchat.networking.socket.SocketNetworkingClient
import kotlinx.coroutines.flow.Flow

class ChatApi : SocketNetworkingClient() {

//    private val baseUrl = "ws://10.0.2.2:8080/socket/chat"
    private val baseUrl = "ws://fdc7-105-163-22-159.in.ngrok.io/socket/chat"

    suspend fun initiateSocket(username: String) {
        initSession(baseUrl, username)
    }

    suspend fun receiveMessage(): Flow<IncomingMessageDto> {
        return receiveData()
    }

    suspend fun sendMessage(message: String, address: String) {
        val request = OutgoingMessageDto(message = message, to = address)
        sendData(body = request)
    }
}
