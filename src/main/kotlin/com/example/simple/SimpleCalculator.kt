package com.example.simple

import com.example.models.AbstractCalculator
import com.example.models.CalculatorException
import com.notkamui.keval.Keval
import com.notkamui.keval.KevalDSLException
import com.notkamui.keval.KevalInvalidExpressionException
import com.notkamui.keval.KevalZeroDivisionException
import com.example.models.AbstractDB

fun String.fixUnaryMinus(): String {
    val components = split('-')
    return buildString {
        for ((index, component) in components.withIndex()) {
            when {
                index == 0 -> {
                    if (component == "") {
                        append('0')
                    }
                }
                components[index - 1].endsWith('(') -> append("0-")
                else -> append("-")
            }
            append(component)
        }
    }
}

class SimpleCalculator(val db: AbstractDB) : AbstractCalculator {
    override fun calculate(stringExpression: String): Double {
        if (stringExpression.contains("--")) {
            throw CalculatorException("Invalid expression!")
        }
        val unaryMinusFixedExpression = stringExpression.replace(" ", "").fixUnaryMinus()
        try {
            val result = Keval.eval(unaryMinusFixedExpression)
            db.insert(stringExpression, result)
            return result
        } catch (e: KevalZeroDivisionException) {
            throw CalculatorException("Division by zero!")
        } catch (e: KevalInvalidExpressionException) {
            throw CalculatorException("Invalid expression!")
        } catch (e: KevalDSLException) {
            throw CalculatorException("DSL exception!")
        }
    }
}
