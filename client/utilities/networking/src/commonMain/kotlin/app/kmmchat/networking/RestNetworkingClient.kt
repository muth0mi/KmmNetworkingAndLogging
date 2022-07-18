package app.kmmchat.networking

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

open class RestNetworkingClient(engine: HttpClientEngine = NetworkingEngine.getEngine()) {

    val client = HttpClient(engine) {
        expectSuccess = true
        HttpResponseValidator {
            handleResponseExceptionWithRequest { exception, _ ->
                if (exception is ClientRequestException) {
                    val exceptionResponse = exception.response
                    val exceptionResponseText = exceptionResponse.bodyAsText()
                    throw Exception(exceptionResponseText)
                } else {
                    throw Exception("Request could not be completed at the moment")
                }
            }
        }
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    suspend inline fun <reified Req, reified Res> post(
        url: String,
        body: Req? = null,
        headers: Map<String, String> = mapOf(),
    ): Res {
        return client.post(url) {
            headers { headers.forEach { (key, value) -> append(key, value) } }
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
    }

    suspend inline fun <reified T> get(
        url: String,
        queryParams: Map<String, Any> = mapOf(),
        headers: Map<String, String> = mapOf(),
    ): T {
        return client.get(url) {
            headers { headers.forEach { (key, value) -> append(key, value) } }
            formData { queryParams.forEach { (key, value) -> parameter(key, value) } }
        }.body()
    }
}
