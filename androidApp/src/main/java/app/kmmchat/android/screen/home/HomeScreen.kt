package app.kmmchat.android.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.kmmchat.Greeting
import app.kmmchat.android.ui.theme.KMM_ChatTheme

fun greet(): String {
    return Greeting().greeting()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

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

            ActionButtons(modifier = Modifier.padding(24.dp))
        }
    }
}

@Composable
fun Heading(modifier: Modifier = Modifier) {
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
fun ActionButtons(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "REST Requests")
        }

        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Socket Requests")
        }

        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Server Sent Events")
        }

        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "GraphQl Requests")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    KMM_ChatTheme {
        HomeScreen()
    }
}