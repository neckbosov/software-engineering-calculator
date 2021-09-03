package com.example.models

interface AbstractHistory {
    fun history(limit: Int): List<CalculationResult>
}
