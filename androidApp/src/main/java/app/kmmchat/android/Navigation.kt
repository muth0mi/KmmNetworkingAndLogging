package app.kmmchat.android

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.kmmchat.android.screen.home.HomeScreen

internal sealed class Screens(val route: String) {
    object Home : Screens("home")
}

@Composable
internal fun Navigation(navController: NavHostController = rememberNavController()) {

    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(Screens.Home.route) { HomeScreen() }
    }
}
