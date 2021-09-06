package com.example.simple

import com.example.models.AbstractCalculator
import com.example.models.CalculatorException
import com.notkamui.keval.Keval
import com.notkamui.keval.KevalDSLException
import com.notkamui.keval.KevalInvalidExpressionException
import com.notkamui.keval.KevalZeroDivisionException

class SimpleCalculator: AbstractCalculator {
    override fun calculate(stringExpression: String): Double {
        try {
            return Keval.eval(stringExpression)
        }
        catch (e: KevalZeroDivisionException)
        {
            throw CalculatorException("Division by zero!")
        }
        catch (e: KevalInvalidExpressionException) {
            throw CalculatorException("Invalid expression!")
        }
        catch (e: KevalDSLException) {
            throw CalculatorException("DSL exception!")
        }
    }
}
