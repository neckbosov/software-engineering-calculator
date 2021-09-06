package com.example.simple

import com.example.models.AbstractHistory
import com.example.models.CalculationInfo

class SimpleHistory: AbstractHistory {
    //private val maxSize = 100
    private var longHistory: MutableList<CalculationInfo> = mutableListOf()

    override fun history(limit: Int): List<CalculationInfo> {
        return longHistory.subList(0, limit).toList()
    }

    //fun addNewCalculation(newCalculation: CalculationInfo){
    //    longHistory.add(0, newCalculation)
    //    if (longHistory.size > maxSize)
    //    {
    //        longHistory = longHistory.subList(0, maxSize)
    //    }
    //}
}
