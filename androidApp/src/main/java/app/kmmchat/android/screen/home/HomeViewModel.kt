package app.kmmchat.android.screen.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    var navigationDestination: MutableState<String?> = mutableStateOf(null)


    fun navigateToDestination(route: String?) {
        navigationDestination.value = route
    }

}