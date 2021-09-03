package com.example.plugins

import com.example.models.*
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.request.*

@Location("/{name}")
internal data class HistoryLocation(val name: String, val limit: Int)

fun Application.configureCalculatorRouting(backend: AbstractBackend) {

    // TODO exceptions catching
    routing {
        post("/calculate") {
            val calculationRequest = call.receive<CalculationRequest>()
            val result = backend.calculate(calculationRequest)
            call.respond(result)
        }

        get<HistoryLocation> {
            val limit = it.limit
            val history = backend.history(limit)
            call.respond(history)
        }
    }

}
