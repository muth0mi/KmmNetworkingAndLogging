package app.kmmchat.android.screen.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
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
import app.kmmchat.android.screen.feed.NewPostScreen
import app.kmmchat.android.ui.theme.KMM_ChatTheme
import app.kmmchat.chat.domain.ChatMessage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ChatScreen(navController: NavHostController, viewModel: ChatViewModel = viewModel()) {

    val navigationBack = viewModel.navigateBack.value
    if (navigationBack) {
        navController.navigateUp()
        viewModel.navigateToLastScreen(false)
    }

    Scaffold(topBar = { AppBar(viewModel) }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            ChatMessages(
                viewModel, modifier = Modifier
                    .padding(12.dp)
                    .weight(1f)
            )

            NewMessageBox(viewModel, modifier = Modifier)
        }
    }
}

@Composable
private fun AppBar(viewModel: ChatViewModel, modifier: Modifier = Modifier) {
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
                    text = "Chat",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                )

                Text(
                    text = "Demonstrate Socket Connections",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                )
            }
        }
    )
}

@Composable
private fun ChatMessages(viewModel: ChatViewModel, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item { Spacer(modifier = Modifier.height(8.dp)) }
        items(viewModel.messages.value) { message ->
            FeedRow(message, modifier = Modifier.padding(vertical = 8.dp))
        }
        item { Spacer(modifier = Modifier.height(8.dp)) }
    }
}

@Composable
private fun FeedRow(chatMessage: ChatMessage, modifier: Modifier = Modifier) {
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
            Text(text = chatMessage.sender, style = MaterialTheme.typography.labelSmall)
            Text(
                text = chatMessage.message, style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
private fun NewMessageBox(viewModel: ChatViewModel, modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Column() {
            BasicTextField(
                value = viewModel.newMessage.value,
                onValueChange = { viewModel.setNewMessage(it) },
                decorationBox = { innerTextField ->
                    if (viewModel.newMessage.value.isEmpty()) Text("Message ...")
                    innerTextField()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .height(64.dp)
            )

            Button(
                onClick = { viewModel.sendMessage() },
                shape = MaterialTheme.shapes.medium,
                content = { Text("Send") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    KMM_ChatTheme {
        ChatScreen(rememberNavController())
    }
}