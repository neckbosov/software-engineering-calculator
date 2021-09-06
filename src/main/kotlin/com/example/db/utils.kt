package com.example.db

import org.jetbrains.exposed.sql.Database

fun connectTestDB() {
    Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", "org.h2.Driver")
}

fun connectPostgresDB(url: String, user: String, password: String) {
    Database.connect("jdbc:postgresql://${url}", driver = "org.postgresql.Driver", user = user, password = password)
}