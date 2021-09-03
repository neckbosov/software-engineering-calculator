package com.example.models

interface AbstractDB {

    fun insert(stringExpression: String, result: Double)

    fun history(limit: Int = 5): List<CalculationResult>

}
