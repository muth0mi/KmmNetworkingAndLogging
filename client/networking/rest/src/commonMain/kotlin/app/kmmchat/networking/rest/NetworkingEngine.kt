package app.kmmchat.networking.rest

import io.ktor.client.engine.*

internal expect object NetworkingEngine {

    fun getEngine(): HttpClientEngine
}
