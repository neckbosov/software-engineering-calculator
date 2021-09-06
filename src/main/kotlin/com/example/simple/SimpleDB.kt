package com.example.simple

import com.example.models.AbstractDB
import com.example.models.CalculationInfo

class SimpleDB: AbstractDB {
    override fun insert(stringExpression: String, result: Double) {
        println("NO-OP DB: ${stringExpression}=${result}")
    }

    override fun history(limit: Int): List<CalculationInfo> {
        println("NO-OP DB: ~~history~~")
        return emptyList()
    }
}
