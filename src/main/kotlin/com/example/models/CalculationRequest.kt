package com.example.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalculationRequest(
    @SerialName("expression")
    val stringExpression: String,
)
