package app.kmmchat.chat.data

import app.kmmchat.Utilities
import app.kmmchat.networking.socket.SocketNetworkingClient
import kotlinx.coroutines.flow.Flow

class ChatApi : SocketNetworkingClient() {

    private val baseUrl = "ws://" + Utilities.localHostUrl + ":8080/socket/chat"

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
