package app.kmmchat.android.screen.chat

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.kmmchat.chat.data.ChatRepositoryImpl
import app.kmmchat.chat.domain.ChatMessage
import app.kmmchat.chat.domain.ChatRepository
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


    var newMessage: MutableState<String> = mutableStateOf("")
    fun setNewMessage(message: String) {
        newMessage.value = message
    }


    var messages: MutableState<List<ChatMessage>> = mutableStateOf(emptyList())
    var sendingPost: MutableState<Boolean> = mutableStateOf(false)
    var sendingPostError: MutableState<String?> = mutableStateOf(null)
    fun sendMessage() = viewModelScope.launch {
        sendingPost.value = true
        kotlin.runCatching {
            chatRepository.sendMessage(newMessage.value)
        }.onFailure {
            sendingPostError.value = it.localizedMessage
        }
        sendingPost.value = false
    }


    private fun connect() = viewModelScope.launch {
        val usernames = listOf("Alpha", "Beta", "Cupcake", "Donut", "ICS", "Kitkat", "Oreo")
        chatRepository.openConnection(usernames.random())
        kotlin.runCatching {
            chatRepository.listenMessages()
                .onEach { message ->
                    messages.value = messages.value.plus(message)
                }.launchIn(viewModelScope)
        }.onFailure {
            println("Failed to listen to messages: " + it.localizedMessage)
        }
    }

    private fun disconnect() = viewModelScope.launch {
        chatRepository.closeConnection()
    }

    init {
        connect()
    }

    override fun onCleared() {
        super.onCleared()
        disconnect()
    }

}