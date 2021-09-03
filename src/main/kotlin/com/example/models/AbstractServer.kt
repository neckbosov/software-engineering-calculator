package com.example.models

interface AbstractServer {
    val calculator: AbstractCalculator
    val history: AbstractHistory
}
