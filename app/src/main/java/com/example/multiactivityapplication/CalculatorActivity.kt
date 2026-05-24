package com.example.multiactivityapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {

    private lateinit var txtDisplay: TextView
    private lateinit var txtPreview: TextView

    private var firstNumber = 0.0
    private var operator = ""
    private var isNewNumber = true

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator)

        txtDisplay = findViewById(R.id.txtDisplay)
        txtPreview = findViewById(R.id.txtPreview)

        // Bottom Navigation Buttons
        val btnPrime = findViewById<Button>(R.id.btnPrime)
        val btnFactorial = findViewById<Button>(R.id.btnFactorial)

        btnPrime.setOnClickListener {

            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }

        btnFactorial.setOnClickListener {

            startActivity(
                Intent(this, FactorialActivity::class.java)
            )
        }

        // Number Buttons
        val numberButtons = listOf(
            R.id.btn0,
            R.id.btn1,
            R.id.btn2,
            R.id.btn3,
            R.id.btn4,
            R.id.btn5,
            R.id.btn6,
            R.id.btn7,
            R.id.btn8,
            R.id.btn9
        )

        for (id in numberButtons) {

            val button = findViewById<Button>(id)

            button.setOnClickListener {

                if (isNewNumber) {

                    txtDisplay.text = button.text
                    isNewNumber = false

                } else {

                    txtDisplay.append(button.text)
                }

                showLiveResult()
            }
        }

        // Dot Button
        findViewById<Button>(R.id.btnDot).setOnClickListener {

            if (isNewNumber) {

                txtDisplay.text = "0."
                isNewNumber = false

            } else {

                if (!txtDisplay.text.contains(".")) {

                    txtDisplay.append(".")
                }
            }
        }

        // Operators
        findViewById<Button>(R.id.btnPlus).setOnClickListener {
            setOperator("+")
        }

        findViewById<Button>(R.id.btnMinus).setOnClickListener {
            setOperator("-")
        }

        findViewById<Button>(R.id.btnMultiply).setOnClickListener {
            setOperator("×")
        }

        findViewById<Button>(R.id.btnDivide).setOnClickListener {
            setOperator("÷")
        }

        // Equal Button
        findViewById<Button>(R.id.btnEqual).setOnClickListener {

            try {

                val secondNumber =
                    txtDisplay.text.toString().toDouble()

                val result =
                    calculateResult(secondNumber)

                txtPreview.text =
                    "$firstNumber $operator $secondNumber ="

                txtDisplay.text =
                    removeZero(result)

                isNewNumber = true

            } catch (e: Exception) {

                txtDisplay.text = "Error"
            }
        }

        // Clear Button
        findViewById<Button>(R.id.btnC).setOnClickListener {

            txtDisplay.text = "0"
            txtPreview.text = ""

            firstNumber = 0.0
            operator = ""
            isNewNumber = true
        }

        // CE Button
        findViewById<Button>(R.id.btnCE).setOnClickListener {

            txtDisplay.text = "0"
            isNewNumber = true
        }

        // Backspace Button
        findViewById<Button>(R.id.btnBack).setOnClickListener {

            val current =
                txtDisplay.text.toString()

            if (current.length > 1) {

                txtDisplay.text =
                    current.dropLast(1)

            } else {

                txtDisplay.text = "0"
                isNewNumber = true
            }
        }

        // Percent Button
        findViewById<Button>(R.id.btnPercent).setOnClickListener {

            try {

                val value =
                    txtDisplay.text.toString().toDouble()

                val result = value / 100

                txtDisplay.text =
                    removeZero(result)

            } catch (e: Exception) {

                txtDisplay.text = "Error"
            }
        }
    }

    // Set Operator
    private fun setOperator(op: String) {

        try {

            firstNumber =
                txtDisplay.text.toString().toDouble()

            operator = op

            txtPreview.text =
                "$firstNumber $operator"

            isNewNumber = true

        } catch (e: Exception) {

            txtDisplay.text = "Error"
        }
    }

    // Live Guess Result
    private fun showLiveResult() {

        if (operator.isEmpty()) return

        try {

            val secondNumber =
                txtDisplay.text.toString().toDouble()

            val result =
                calculateResult(secondNumber)

            txtPreview.text =
                "$firstNumber $operator $secondNumber = ${removeZero(result)}"

        } catch (e: Exception) {

        }
    }

    // Calculate Result
    private fun calculateResult(secondNumber: Double): Double {

        return when (operator) {

            "+" -> firstNumber + secondNumber

            "-" -> firstNumber - secondNumber

            "×" -> firstNumber * secondNumber

            "÷" -> firstNumber / secondNumber

            else -> secondNumber
        }
    }

    // Remove .0
    private fun removeZero(value: Double): String {

        return if (value % 1 == 0.0) {

            value.toInt().toString()

        } else {

            value.toString()
        }
    }
}