package app.kmmchat.chat.domain

import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    @Throws(Exception::class)
    suspend fun openConnection(username: String)

    @Throws(Exception::class)
    suspend fun closeConnection()

    @Throws(Exception::class)
    suspend fun listenMessages(): Flow<ChatMessage>

    @Throws(Exception::class)
    suspend fun sendMessage(message: String)
}