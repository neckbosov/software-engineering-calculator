package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class History(
    val history: List<CalculationInfo>
)
