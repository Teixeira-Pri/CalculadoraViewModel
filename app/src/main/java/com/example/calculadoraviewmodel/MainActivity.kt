package com.example.calculadoraviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CalculatorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java)

        // ações dos botões da calculadora
        number_0.setOnClickListener { viewModel.inputNumber("0") }
        number_1.setOnClickListener { viewModel.inputNumber("1") }
        number_2.setOnClickListener { viewModel.inputNumber("2") }
        number_3.setOnClickListener { viewModel.inputNumber("3") }
        number_4.setOnClickListener { viewModel.inputNumber("4") }
        number_5.setOnClickListener { viewModel.inputNumber("5") }
        number_6.setOnClickListener { viewModel.inputNumber("6") }
        number_7.setOnClickListener { viewModel.inputNumber("7") }
        number_8.setOnClickListener { viewModel.inputNumber("8") }
        number_9.setOnClickListener { viewModel.inputNumber("9") }
        btn_sum.setOnClickListener { viewModel.inputOperator("+") }
        btn_subtraction.setOnClickListener { viewModel.inputOperator("-") }
        btn_multiplication.setOnClickListener { viewModel.inputOperator("*") }
        btn_divide.setOnClickListener { viewModel.inputOperator("/") }
        btn_equal.setOnClickListener { viewModel.calculate() }
        clear.setOnClickListener { viewModel.clear() }


        // observar as mudanças no display do resultado
        viewModel.display.observe(this, Observer { result -> txt_resultado.text = result })

        // observar as mudanças no display da expressao
        viewModel.expression.observe(this, Observer { expression -> expressao.text = expression })
    }


}