package app.kmmchat.networking

import io.ktor.client.engine.*

internal expect object NetworkingEngine {

    fun getEngine(): HttpClientEngine
}
