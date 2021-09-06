package com.example.simple

import com.example.models.AbstractCalculator
import com.example.models.CalculatorException
import com.notkamui.keval.Keval
import com.notkamui.keval.KevalDSLException
import com.notkamui.keval.KevalInvalidExpressionException
import com.notkamui.keval.KevalZeroDivisionException
import com.example.models.AbstractDB

class SimpleCalculator(val db: AbstractDB) : AbstractCalculator {
    override fun calculate(stringExpression: String): Double {
        try {
            val result = Keval.eval(stringExpression)
            println("NO-OP calculator: ${stringExpression} = %d!".format(result))
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
