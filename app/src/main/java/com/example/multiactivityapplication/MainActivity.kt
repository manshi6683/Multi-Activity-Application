package com.example.multiactivityapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNumber: EditText
    private lateinit var txtResult: TextView
    private lateinit var btnCheck: Button
    private lateinit var btnFactorial: Button
    private lateinit var btnCalculator: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNumber = findViewById(R.id.etNumber)
        txtResult = findViewById(R.id.txtResult)

        btnCheck = findViewById(R.id.btnCheck)
        btnFactorial = findViewById(R.id.btnFactorial)
        btnCalculator = findViewById(R.id.btnCalculator)

        // Prime Check
        btnCheck.setOnClickListener {

            val numberText = etNumber.text.toString()

            if (numberText.isEmpty()) {
                txtResult.text = "Enter a number"
                return@setOnClickListener
            }

            val number = numberText.toInt()

            if (isPrime(number)) {
                txtResult.text = "$number is Prime"
            } else {
                txtResult.text = "$number is Not Prime"
            }
        }

        // Open Factorial Page
        btnFactorial.setOnClickListener {

            startActivity(
                Intent(this, FactorialActivity::class.java)
            )
        }

        // Open Calculator Page
        btnCalculator.setOnClickListener {

            startActivity(
                Intent(this, CalculatorActivity::class.java)
            )
        }
    }

    private fun isPrime(num: Int): Boolean {

        if (num <= 1) {
            return false
        }

        for (i in 2 until num) {

            if (num % i == 0) {
                return false
            }
        }

        return true
    }
}