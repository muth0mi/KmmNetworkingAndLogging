package app.kmmchat.android

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.kmmchat.android.screen.chat.ChatScreen
import app.kmmchat.android.screen.chat.ChatViewModel
import app.kmmchat.android.screen.feed.FeedScreen
import app.kmmchat.android.screen.home.HomeScreen
import app.kmmchat.chat.presentation.ChatScreenEvent

internal sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Feed : Screens("feed")
    object Chat : Screens("chat")
}

@Composable
internal fun Navigation(navController: NavHostController = rememberNavController()) {

    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(Screens.Home.route) { HomeScreen(navController) }
        composable(Screens.Feed.route) { FeedScreen(navController) }
        composable(Screens.Chat.route) {
            val viewModel: ChatViewModel = viewModel()
            val state = viewModel.state.value
            val performEvent = viewModel::performEvent
            if (state.navigatingUp) {
                navController.navigateUp()
                performEvent(ChatScreenEvent.NavigateUp(false))
            }
            ChatScreen(state, performEvent)
        }
    }
}
