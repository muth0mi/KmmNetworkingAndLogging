package app.kmmchat.networking.socket

import io.ktor.client.engine.*

internal expect object NetworkingEngine {

    fun getEngine(): HttpClientEngine
}
