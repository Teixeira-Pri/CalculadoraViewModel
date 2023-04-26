package com.example.calculadoraviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt

class CalculatorViewModel:ViewModel() {

    private var leftOperand: Int? = null
    private var rightOperand: Int? = null
    private var currentOperator: String? = null
    private var currentExpression: String = ""
    var currentValue: String = "0"

    private val _display = MutableLiveData<String>()
    val display: LiveData<String>
    get() = _display

    private val _expression = MutableLiveData<String>()
    val expression: LiveData<String>
    get() = _expression

    init {
        _display.value = "0"
        _expression.value = ""
    }

    fun inputNumber(number: String){
        if(currentOperator == null){
            leftOperand = (leftOperand ?: 0) * 10 + number.toInt()
            _display.value = leftOperand.toString()
            currentExpression += number
            _expression.value = currentExpression
        } else {
            rightOperand = (rightOperand ?: 0) * 10 + number.toInt()
            _display.value = rightOperand.toString()
            currentExpression += number
            _expression.value = currentExpression
        }
    }

    fun inputOperator(operator: String){
        currentOperator = operator
        currentExpression += " $operator "
        _expression.value = currentExpression
    }

    fun calculate(){
        if (leftOperand != null && rightOperand != null && currentOperator != null){
            val result = when (currentOperator){
                "+" -> leftOperand!! + rightOperand!!
                "-" -> leftOperand!! - rightOperand!!
                "*" -> leftOperand!! * rightOperand!!
                "/" -> leftOperand!! / rightOperand!!
                else -> null
            }
            if (result != null){
                _display.value = result.toString()
                currentExpression += " = $result"
                _expression.value = currentExpression
                leftOperand = result
                rightOperand = null
                currentOperator = null
            }
        }
    }

    fun clear(){
        leftOperand = null
        rightOperand = null
        currentOperator = null
        currentExpression = ""
        _display.value = "0"
        _expression.value = ""
    }
}