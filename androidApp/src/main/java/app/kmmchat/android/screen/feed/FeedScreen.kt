package app.kmmchat.android.screen.feed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.kmmchat.android.ui.theme.KMM_ChatTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FeedScreen(navController: NavHostController) {

    Scaffold(
        topBar = { AppBar() },
        floatingActionButton = { Fab() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            FeedList(modifier = Modifier.padding(24.dp))
        }
    }
}

@Composable
private fun AppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Feed",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                )

                Text(
                    text = "Demonstrate REST Requests",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                )
            }
        }
    )
}

@Composable
private fun Fab(modifier: Modifier = Modifier) {
    ExtendedFloatingActionButton(
        modifier = modifier,
        onClick = {}
    ) {
        Text(text = "New Post")
    }
}

@Composable
private fun FeedList(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {

    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    KMM_ChatTheme {
        FeedScreen(rememberNavController( ))
    }
}