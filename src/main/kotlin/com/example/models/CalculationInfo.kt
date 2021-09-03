package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class CalculationInfo(
    val stringExpression: String,
    val result: Double,
)
