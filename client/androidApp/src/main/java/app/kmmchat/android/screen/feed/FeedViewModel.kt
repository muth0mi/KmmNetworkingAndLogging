package app.kmmchat.android.screen.feed

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.kmmchat.feed.domain.FeedItem
import app.kmmchat.feed.domain.FeedRepository
import app.kmmchat.feed.data.FeedRepositoryImpl
import kotlinx.coroutines.launch

class FeedViewModel(
    private val feedRepository: FeedRepository = FeedRepositoryImpl()
) : ViewModel() {

    var navigateBack: MutableState<Boolean> = mutableStateOf(false)

    fun navigateToLastScreen(navigate: Boolean = true) {
        navigateBack.value = navigate
    }


    var navigationDestination: MutableState<String?> = mutableStateOf(null)

    fun navigateToDestination(route: String?) {
        navigationDestination.value = route
    }


    var newPostAlertVisible: MutableState<Boolean> = mutableStateOf(false)

    fun setNewPostAlertVisible(creating: Boolean = true) {
        newPostAlertVisible.value = creating
    }


    var error: MutableState<String?> = mutableStateOf(null)
    var feedItems: MutableState<List<FeedItem>> = mutableStateOf(emptyList())
    var refreshingFeedItems: MutableState<Boolean> = mutableStateOf(false)

    fun refreshFeedItems() = viewModelScope.launch {
        error.value = null
        refreshingFeedItems.value = true
        kotlin.runCatching {
            feedRepository.getFeedItems()
        }.onSuccess {
            feedItems.value = it
        }.onFailure {
            error.value = it.localizedMessage
        }
        refreshingFeedItems.value = false
    }


    init {
        refreshFeedItems()
    }
}