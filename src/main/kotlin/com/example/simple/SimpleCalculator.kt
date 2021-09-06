package com.example.simple

import com.example.models.AbstractCalculator
import com.example.models.AbstractDB

class SimpleCalculator(val db: AbstractDB): AbstractCalculator {
    override fun calculate(stringExpression: String): Double {
        println("NO-OP calculator: ${stringExpression} = 42!")
        val result = 42.0
        db.insert(stringExpression, result)
        return result
    }
}
