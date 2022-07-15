package app.kmmchat.android.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.kmmchat.android.Screens
import app.kmmchat.android.ui.theme.KMM_ChatTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = viewModel()) {

    val navigationDestination =  viewModel.navigationDestination.value
    if (navigationDestination != null){
        navController.navigate(navigationDestination)
        viewModel.navigateToDestination(null)
    }


    Scaffold() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            Heading(
                modifier = Modifier
                    .weight(1f)
                    .padding(24.dp)
            )

            ActionButtons(viewModel, modifier = Modifier.padding(24.dp))
        }
    }
}

@Composable
private fun Heading(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Network Calls and Logging in KMM",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier
        )
    }
}

@Composable
private fun ActionButtons(viewModel: HomeViewModel, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Button(
            content = { Text(text = "REST Requests") },
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.navigateToDestination(Screens.Feed.route) },
        )

        Button(
            content = { Text(text = "Socket Requests") },
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.navigateToDestination(Screens.Feed.route) },
        )

        Button(
            content = { Text(text = "Server Sent Events") },
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.navigateToDestination(Screens.Feed.route) },
        )

        Button(
            content = { Text(text = "GraphQl Requests") },
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.navigateToDestination(Screens.Feed.route) },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    KMM_ChatTheme {
        HomeScreen(rememberNavController())
    }
}