package com.example.simple

import com.example.models.*

class SimpleBackend(
    val calculator: SimpleCalculator,
    val history: SimpleHistory,
) : AbstractBackend {
    override fun calculate(calculationRequest: CalculationRequest): CalculationResult {
        TODO("Not yet implemented")
    }

    override fun history(limit: Int): History {
        TODO("Not yet implemented")
    }
}
