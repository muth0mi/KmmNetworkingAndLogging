package app.kmmchat.android.screen.chat

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.kmmchat.android.ui.theme.KMM_ChatTheme
import app.kmmchat.chat.domain.ChatMessage
import app.kmmchat.chat.presentation.ChatScreenEvent
import app.kmmchat.chat.presentation.ChatScreenState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ChatScreen(state: ChatScreenState, performEvent: (ChatScreenEvent) -> Unit) {

    Scaffold(
        topBar = { AppBar { performEvent(ChatScreenEvent.NavigateUp()) } },
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {

                ChatMessages(
                    messages = state.messages,
                    modifier = Modifier.weight(1f)
                )

                NewMessageBox(
                    modifier = Modifier,
                    messageText = state.messageText,
                    onEditMessage = { m -> performEvent(ChatScreenEvent.TypeMessageText(m)) },
                    onClickSend = { performEvent(ChatScreenEvent.SendMessage) }
                )
            }
        }
    )
}

@Composable
private fun AppBar(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
) {

    CenterAlignedTopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton(
                content = { Icon(Icons.Default.ArrowBack, null) },
                onClick = onClickBack
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
private fun ChatMessages(
    modifier: Modifier = Modifier,
    messages: List<ChatMessage>,
) {

    LazyColumn(modifier = modifier.fillMaxSize()) {

        item { Spacer(modifier = Modifier.height(8.dp)) }

        items(messages) { ChatRow(modifier = Modifier.padding(8.dp), it) }

        item { Spacer(modifier = Modifier.height(8.dp)) }
    }
}

@Composable
private fun ChatRow(
    modifier: Modifier = Modifier,
    chatMessage: ChatMessage,
) {

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

            Text(
                text = chatMessage.sender,
                style = MaterialTheme.typography.labelSmall
            )

            Text(
                text = chatMessage.message, style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
private fun NewMessageBox(
    modifier: Modifier = Modifier,
    messageText: String,
    onEditMessage: (String) -> Unit,
    onClickSend: () -> Unit,
) {

    Surface(color = MaterialTheme.colorScheme.surfaceVariant, modifier = modifier) {

        Column {

            BasicTextField(
                value = messageText,
                onValueChange = { onEditMessage(it) },
                decorationBox = { innerTextField ->
                    if (messageText.isEmpty()) Text("Message ...")
                    innerTextField()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(64.dp)
            )

            Button(
                onClick = onClickSend,
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
        ChatScreen(ChatScreenState()) {}
    }
}