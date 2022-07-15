package app.kmmchat.android.screen.feed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import app.kmmchat.FeedItem
import app.kmmchat.android.ui.theme.KMM_ChatTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FeedScreen(navController: NavHostController, viewModel: FeedViewModel = viewModel()) {

    val navigationBack = viewModel.navigateBack.value
    if (navigationBack) {
        navController.navigateUp()
        viewModel.navigateToLastScreen(false)
    }

    val navigationDestination = viewModel.navigationDestination.value
    if (navigationDestination != null) {
        navController.navigate(navigationDestination)
        viewModel.navigateToDestination(null)
    }

    if (viewModel.newPostAlertVisible.value) {
        NewPostScreen(onDismiss = { viewModel.setNewPostAlertVisible(false) })
    }

    Scaffold(
        topBar = { AppBar(viewModel) },
        floatingActionButton = { Fab(viewModel) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            if (!viewModel.error.value.isNullOrBlank()) {
                Text(
                    text = viewModel.error.value.orEmpty(),
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(24.dp)
                )

                Button(content = { Text("Retry") }, onClick = { viewModel.refreshFeedItems() })

            } else {
                FeedList(viewModel, modifier = Modifier.padding(12.dp))
            }
        }
    }
}

@Composable
private fun AppBar(viewModel: FeedViewModel, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton(
                content = { Icon(Icons.Default.ArrowBack, null) },
                onClick = { viewModel.navigateToLastScreen() }
            )
        },
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
private fun Fab(viewModel: FeedViewModel, modifier: Modifier = Modifier) {
    ExtendedFloatingActionButton(
        modifier = modifier,
        onClick = { viewModel.setNewPostAlertVisible() }
    ) {
        Text(text = "New Post")
    }
}

@Composable
private fun FeedList(viewModel: FeedViewModel, modifier: Modifier = Modifier) {

    SwipeRefresh(
        modifier = modifier.fillMaxSize(),
        state = rememberSwipeRefreshState(viewModel.refreshingFeedItems.value),
        onRefresh = { viewModel.refreshFeedItems() },
    ) {
        LazyColumn(modifier = modifier.fillMaxWidth()) {
            item { Spacer(modifier = Modifier.height(8.dp)) }
            items(viewModel.feedItems.value) { feedItem ->
                FeedRow(feedItem, modifier = Modifier.padding(vertical = 8.dp))
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
        }
    }
}

@Composable
private fun FeedRow(feedItem: FeedItem, modifier: Modifier = Modifier) {
    Surface(
        tonalElevation = 4.dp,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(text = feedItem.post)
            Text(text = feedItem.author, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    KMM_ChatTheme {
        FeedScreen(rememberNavController())
    }
}