package app.kmmchat.chat.data

import app.kmmchat.chat.domain.ChatMessage
import app.kmmchat.chat.domain.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChatRepositoryImpl(
    private val chatApi: ChatApi = ChatApi()
) : ChatRepository {

    override suspend fun openConnection(username: String) {
        chatApi.initiateSocket(username)
    }

    override suspend fun closeConnection() {
        chatApi.closeSession()
    }

    @Throws(Exception::class)
    override suspend fun listenMessages(): Flow<ChatMessage> {
        return chatApi.receiveMessage().map {
            ChatMessage(message = it.message, sender = it.from)
        }
    }

    @Throws(Exception::class)
    override suspend fun sendMessage(message: String) {
        chatApi.sendMessage(message, "author 1")
    }
}