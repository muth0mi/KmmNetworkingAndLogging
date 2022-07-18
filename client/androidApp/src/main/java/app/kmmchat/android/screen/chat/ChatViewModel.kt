package app.kmmchat.android.screen.chat

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.kmmchat.chat.data.ChatRepositoryImpl
import app.kmmchat.chat.domain.ChatMessage
import app.kmmchat.chat.domain.ChatRepository
import app.kmmchat.logging.Logger
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ChatViewModel(
    private val chatRepository: ChatRepository = ChatRepositoryImpl()
) : ViewModel() {

    var navigateBack: MutableState<Boolean> = mutableStateOf(false)
    fun navigateToLastScreen(navigate: Boolean = true) {
        navigateBack.value = navigate
    }


    var messages: MutableState<List<ChatMessage>> = mutableStateOf(emptyList())


    var newMessage: MutableState<String> = mutableStateOf("")
    fun setNewMessage(message: String) {
        newMessage.value = message
    }


    var error: MutableState<String?> = mutableStateOf(null)
    var sendingMessage: MutableState<Boolean> = mutableStateOf(false)
    fun sendMessage() = viewModelScope.launch {
        Logger.i("Sending message: message -> " + newMessage.value, "ChatViewModel")

        error.value = null
        sendingMessage.value = true
        kotlin.runCatching {
            chatRepository.sendMessage(newMessage.value)
        }.onFailure {
            error.value = it.localizedMessage
        }
        sendingMessage.value = false
    }


    private fun connect() = viewModelScope.launch {
        Logger.i("Listening for new messages", "ChatViewModel")

        val usernames = listOf("Alpha", "Beta", "Cupcake", "Donut", "ICS", "Kitkat", "Oreo")
        chatRepository.openConnection(usernames.random())
        kotlin.runCatching {
            chatRepository.listenMessages()
                .onEach { message ->
                    messages.value = messages.value.plus(message)
                }.launchIn(viewModelScope)
        }.onFailure {
            Logger.w(it.localizedMessage.orEmpty(), "ChatViewModel")
            println("Failed to listen to messages: " + it.localizedMessage)
        }
    }

    private fun disconnect() = viewModelScope.launch {
        chatRepository.closeConnection()
    }

    init {
        Logger.i("Chat viewmodel launched Started", "ChatViewModel")
        connect()
    }

    override fun onCleared() {
        super.onCleared()
        disconnect()
    }

}