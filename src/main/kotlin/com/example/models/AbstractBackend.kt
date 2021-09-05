package com.example.models

interface AbstractBackend {
    fun calculate(calculationRequest: CalculationRequest): CalculationResult
    fun history(limit: Int): History
}
