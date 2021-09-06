package com.example.db

import com.example.models.AbstractDB
import com.example.models.CalculationInfo
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object ExposedDB : AbstractDB {
    object CalculationLogs : Table() {
        val id = integer("id").autoIncrement()
        val expr = text("expression")
        val result = double("result")

        override val primaryKey = PrimaryKey(id)
    }

    init {
        transaction {
            SchemaUtils.create(CalculationLogs)
            commit()
        }
    }

    override fun insert(stringExpression: String, result: Double) {
        transaction {
            CalculationLogs.insert {
                it[this.expr] = stringExpression
                it[this.result] = result
            }
            commit()
        }
    }

    override fun history(limit: Int): List<CalculationInfo> =
        transaction {
            CalculationLogs.selectAll()
                .orderBy(CalculationLogs.id, SortOrder.DESC)
                .limit(limit)
                .map { CalculationInfo(it[CalculationLogs.expr], it[CalculationLogs.result]) }
        }
}