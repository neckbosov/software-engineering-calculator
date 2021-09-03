package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class History(
    val infoList: List<CalculationInfo>
)
