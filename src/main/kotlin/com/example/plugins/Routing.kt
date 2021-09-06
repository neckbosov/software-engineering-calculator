package com.example.plugins

import com.example.models.*
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.request.*
import kotlinx.serialization.SerializationException

@OptIn(KtorExperimentalLocationsAPI::class)
fun Application.configureCalculatorRouting(backend: AbstractBackend) {

    // TODO exceptions catching
    routing {
        post("/calculate") {
            try {
                val calculationRequest = call.receive<CalculationRequest>()
                val result = backend.calculate(calculationRequest)
                call.respond(status = HttpStatusCode.OK, result)
            } catch (e: CalculatorException) {
                val error = ErrorResponse(e.description)
                call.respond(status = HttpStatusCode.NotFound, error)
            } catch (e: SerializationException) {
                call.respond(status = HttpStatusCode.BadRequest, "Ill-formed payload")
            }
        }

        get("/history") {
            val limit = (call.request.queryParameters["limit"] ?: "10").toIntOrNull()
            if (limit == null || limit < 0 || limit > 100) {
                call.respond(status = HttpStatusCode.BadRequest, "limit query-arg should be an integer from 0 to 100")
                return@get
            }
            val history = backend.history(limit)
            call.respond(history)
        }
    }

}
