package app.kmmchat.chat.data

import app.kmmchat.networking.SocketNetworkingClient
import app.kmmchat.networking.NetworkUtilities
import kotlinx.coroutines.flow.Flow

class ChatApi : SocketNetworkingClient() {

    private val baseUrl = "ws://" + NetworkUtilities.localHost + ":8080/socket/chat"

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
