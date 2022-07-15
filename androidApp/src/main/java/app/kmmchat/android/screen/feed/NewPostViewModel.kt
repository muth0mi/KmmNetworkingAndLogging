package app.kmmchat.android.screen.feed

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.kmmchat.FeedRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class NewPostViewModel(
    private val feedRepository: FeedRepository = FeedRepository()
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
        delay(2000)
        feedRepository.postToFeed(newPostText.value)
        if (Random.nextBoolean()) {
            setPostSentSuccessfully(true)
        } else {
            setErrorMessage("Some Error")
        }
        postingFeed.value = false
    }
}