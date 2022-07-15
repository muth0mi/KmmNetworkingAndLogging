package app.kmmchat.android.screen.feed

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.kmmchat.domain.FeedRepository
import app.kmmchat.data.FeedRepositoryImpl
import kotlinx.coroutines.launch

class NewPostViewModel(
    private val feedRepository: FeedRepository = FeedRepositoryImpl()
) : ViewModel() {

    var postSent: MutableState<Boolean> = mutableStateOf(false)

    fun setPostSentSuccessfully(sent: Boolean = true) {
        postSent.value = sent
    }


    var error: MutableState<String?> = mutableStateOf(null)

    fun setErrorMessage(message: String?) {
        error.value = message
    }


    var newPostText: MutableState<String> = mutableStateOf("")

    fun setNewPostText(text: String) {
        newPostText.value = text
    }


    var postingFeed: MutableState<Boolean> = mutableStateOf(false)

    fun postNewFeed() = viewModelScope.launch {
        setErrorMessage(null)
        postingFeed.value = true
        kotlin.runCatching {
            feedRepository.postToFeed(newPostText.value)
        }.onSuccess {
            setPostSentSuccessfully(true)
        }.onFailure {
            setErrorMessage(it.localizedMessage)
        }
        postingFeed.value = false
    }
}