package com.example.simple

import com.example.models.AbstractCalculator
import com.example.models.CalculatorException
import com.notkamui.keval.Keval
import com.notkamui.keval.KevalDSLException
import com.notkamui.keval.KevalInvalidExpressionException
import com.notkamui.keval.KevalZeroDivisionException
import com.example.models.AbstractDB

fun String.insert(index: Int, value: String): String {
    return take(index) + value + takeLast(length - index)
}

fun String.fixUnaryMinus(): String {
    var res = this
    for ((index, value) in this.withIndex()) {
        if (value == '-') {
            if (index == 0) {
                res = res.insert(index, "0")
            } else if (this[index-1] == '(') {
                res = res.insert(index, "0")
            }
        }
    }
    return res
}

class SimpleCalculator(val db: AbstractDB) : AbstractCalculator {
    override fun calculate(stringExpression: String): Double {
        val unaryMinusFixedExpression = stringExpression.fixUnaryMinus()
        try {
            val result = Keval.eval(stringExpression)
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
