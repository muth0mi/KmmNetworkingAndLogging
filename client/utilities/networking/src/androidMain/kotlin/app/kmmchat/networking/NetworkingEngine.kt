package app.kmmchat.networking

import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*

internal actual object NetworkingEngine {

    actual fun getEngine(): HttpClientEngine {
        return OkHttp.create()
    }
}
