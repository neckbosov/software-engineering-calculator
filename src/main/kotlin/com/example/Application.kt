package com.example

import com.example.db.ExposedDB
import com.example.db.connectPostgresDB
import com.example.db.connectTestDB
import io.ktor.server.engine.*
import io.ktor.features.*
import io.ktor.server.netty.*
import io.ktor.application.*
import com.example.plugins.*
import com.example.simple.SimpleBackend
import com.example.simple.SimpleCalculator
import com.example.simple.SimpleHistory
import io.ktor.locations.*
import io.ktor.serialization.*
import kotlinx.cli.*
import kotlinx.serialization.json.Json

fun main(args: Array<String>) {
    val parser = ArgParser("Calculator Backend")
    val dbURI by parser.option(ArgType.String, shortName = "db", description = "Postgres DB connection URI, use `test` to use in-memory test DB").required()
    val dbUser by parser.option(ArgType.String, shortName = "u", description = "DB user")
    val dbPassword by parser.option(ArgType.String, shortName = "pwd", description = "DB password")
    parser.parse(args)

    if (dbURI == "test") {
        connectTestDB()
    } else {
        connectPostgresDB(dbURI, dbUser ?: "", dbPassword ?: "")
    }

    val backend = SimpleBackend(SimpleCalculator(ExposedDB), SimpleHistory(ExposedDB))

    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json()
        }
        install(Locations)
        configureCalculatorRouting(backend)
    }.start(wait = true)
}
