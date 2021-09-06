package com.example.simple

import com.example.models.AbstractDB
import com.example.models.AbstractHistory
import com.example.models.CalculationInfo

class SimpleHistory(val db: AbstractDB): AbstractHistory {
    override fun history(limit: Int): List<CalculationInfo> = db.history(limit)
}
