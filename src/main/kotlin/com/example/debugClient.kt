package com.example
import com.example.models.CalculationRequest
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

suspend fun main() {
    val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    val response: String = client.post("http://0.0.0.0:8080/calculate") {

        contentType(ContentType.Application.Json)
        body = CalculationRequest("1+3")

    }


    println(response)
    client.close()
}