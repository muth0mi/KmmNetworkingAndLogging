package app.kmmchat.android.screen.chat

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.kmmchat.chat.domain.ChatMessage
import app.kmmchat.domain.FeedItem
import app.kmmchat.domain.FeedRepository
import app.kmmchat.data.FeedRepositoryImpl
import kotlinx.coroutines.launch

class ChatViewModel(
    private val feedRepository: FeedRepository = FeedRepositoryImpl()
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
            feedRepository.getFeedItems()
        }.onSuccess {
            messages.value = messages.value.plus(ChatMessage("dd",""))
        }.onFailure {
            sendingPostError.value = it.localizedMessage
        }
        sendingPost.value = false
    }


}