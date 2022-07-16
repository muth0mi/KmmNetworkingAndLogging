package app.kmmchat.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import app.kmmchat.android.ui.theme.KMM_ChatTheme
import app.kmmchat.logging.kermit

internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        kermit.withTag("MainActivity").i { "Activity Started" }

        super.onCreate(savedInstanceState)
        setContent {
            KMM_ChatTheme {
                Navigation()
            }
        }
    }
}
