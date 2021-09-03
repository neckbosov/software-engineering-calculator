package com.example

import io.ktor.server.engine.*
import io.ktor.features.*
import io.ktor.server.netty.*
import io.ktor.application.*
import com.example.plugins.*
import com.example.simple.createSimpleBackend
import io.ktor.locations.*
import io.ktor.serialization.*

fun main() {
    val backend = createSimpleBackend()

    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json()
        }
        install(Locations)
        configureCalculatorRouting(backend)
    }.start(wait = true)
}
