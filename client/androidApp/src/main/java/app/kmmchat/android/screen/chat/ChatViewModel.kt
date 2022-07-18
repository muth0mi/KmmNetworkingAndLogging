package app.kmmchat.android.screen.chat

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.kmmchat.chat.data.ChatRepositoryImpl
import app.kmmchat.chat.domain.ChatRepository
import app.kmmchat.chat.presentation.ChatScreenEvent
import app.kmmchat.chat.presentation.ChatScreenState
import app.kmmchat.logging.Logger
import kotlinx.coroutines.launch

class ChatViewModel(
    private val chatRepository: ChatRepository = ChatRepositoryImpl()
) : ViewModel() {

    private val _state: MutableState<ChatScreenState> = mutableStateOf(ChatScreenState())
    val state get() = _state


    fun performEvent(event: ChatScreenEvent) {
        val state = state.value
        Logger.v("Performing action: $event on state: $state", "ChatViewModel")

        when (event) {
            is ChatScreenEvent.NavigateUp -> {
                _state.value = state.copy(navigatingUp = event.navigate)
            }
            is ChatScreenEvent.SendMessage -> {
                sendMessage(state.messageText)
            }
            is ChatScreenEvent.DisplayMessage -> {
                _state.value = state.copy(messages = state.messages.plus(event.message))
            }
            is ChatScreenEvent.TypeMessageText -> {
                _state.value = state.copy(messageText = event.text)
            }
            is ChatScreenEvent.OpenConnection -> {
                val usernames = listOf("Alpha", "Beta", "Cupcake", "Donut", "ICS", "Kitkat", "Oreo")
                val username = usernames.random()
                connect(username)
            }
            is ChatScreenEvent.CloseConnection -> {
                disconnect()
            }
            is ChatScreenEvent.ObserveMessages -> {
                listenMessages()
            }
        }
    }


    private fun sendMessage(message: String) = viewModelScope.launch {
        Logger.i("Sending message: content -> $message", "ChatViewModel")

        kotlin.runCatching {
            chatRepository.sendMessage(message)
        }.onFailure {
            Logger.i("Could not send message: stacktrace -> ${it.stackTrace}", "ChatViewModel")
        }
    }


    private fun listenMessages() = viewModelScope.launch {
        Logger.i("Listening for new messages", "ChatViewModel")

        kotlin.runCatching {
            chatRepository.listenMessages().collect {
                Logger.v("New message received: $it", "ChatViewModel")
                performEvent(ChatScreenEvent.DisplayMessage(it))
            }
        }.onFailure {
            Logger.i("Could not observe messages: stacktrace -> ${it.stackTrace}", "ChatViewModel")
        }
    }


    private fun connect(username: String) = viewModelScope.launch {
        Logger.i("Opening chat connection for user: $username", "ChatViewModel")

        kotlin.runCatching {
            chatRepository.openConnection(username)
            performEvent(ChatScreenEvent.ObserveMessages)
        }.onFailure {
            Logger.i("Could not open connection: stacktrace -> ${it.stackTrace}", "ChatViewModel")
        }
    }


    private fun disconnect() = viewModelScope.launch {
        Logger.i("Closing chat connection", "ChatViewModel")

        chatRepository.closeConnection()
    }


    init {
        performEvent(ChatScreenEvent.OpenConnection)
    }

    override fun onCleared() {
        super.onCleared()
        performEvent(ChatScreenEvent.CloseConnection)
    }
}
