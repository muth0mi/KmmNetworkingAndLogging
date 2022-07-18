package app.kmmchat.android.screen.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.kmmchat.android.ui.theme.KMM_ChatTheme

@Composable
internal fun NewPostScreen(
    onDismiss: () -> Unit,
    viewModel: NewPostViewModel = viewModel()
) {

    if (viewModel.postSent.value) {
        viewModel.setErrorMessage(null)
        viewModel.setPostSentSuccessfully(false)
        onDismiss()
    }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "New Post",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.medium
            ) {
                BasicTextField(
                    value = viewModel.newPostText.value,
                    onValueChange = { viewModel.setNewPostText(it) },
                    decorationBox = { innerTextField ->
                        if (viewModel.newPostText.value.isEmpty()) Text("Post your status ...")
                        innerTextField()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .height(98.dp)
                )
            }
        },
        confirmButton = {
            Column() {
                if (!viewModel.error.value.isNullOrBlank())
                    Text(
                        text = viewModel.error.value.orEmpty(),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    )

                Button(
                    enabled = !viewModel.postingFeed.value,
                    content = { Text("Post Update") },
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.postNewFeed() }
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    KMM_ChatTheme {
        NewPostScreen({})
    }
}