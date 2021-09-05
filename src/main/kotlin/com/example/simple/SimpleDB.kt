package com.example.simple

import com.example.models.AbstractDB
import com.example.models.CalculationInfo

class SimpleDB: AbstractDB {
    override fun insert(stringExpression: String, result: Double) {
        TODO("Not yet implemented")
    }

    override fun history(limit: Int): List<CalculationInfo> {
        TODO("Not yet implemented")
    }
}
