package app.kmmchat.networking

import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*

internal actual object NetworkingEngine {

    actual fun getEngine(): HttpClientEngine {
        return Darwin.create()
    }
}
