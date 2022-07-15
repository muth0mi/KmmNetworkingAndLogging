package app.kmmchat.android.screen.feed

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class FeedViewModel : ViewModel() {

    var navigateBack: MutableState<Boolean> = mutableStateOf(false)

    fun navigateToLastScreen(navigate: Boolean = true) {
        navigateBack.value = navigate
    }

    var navigationDestination: MutableState<String?> = mutableStateOf(null)

    fun navigateToDestination(route: String?) {
        navigationDestination.value = route
    }


}