package com.example.simple

import com.example.models.*

class SimpleBackend(
    private val calculator: SimpleCalculator,
    private val history: SimpleHistory,
) : AbstractBackend {
    override fun calculate(calculationRequest: CalculationRequest): CalculationResult {
        return CalculationResult(
            calculator.calculate(calculationRequest.stringExpression)
        )
    }

    override fun history(limit: Int): History {
        return History(history.history(limit))
    }
}
