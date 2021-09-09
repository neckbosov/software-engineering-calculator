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
       body = CalculationRequest("-5+(-1+3)+(-6-7)")
        //body = CalculationRequest("0-5+(0-1+3)+(0-6-7)")
    }


    println(response)
    client.close()
}