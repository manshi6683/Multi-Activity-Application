package com.example.multiactivityapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FactorialActivity : AppCompatActivity() {

    private lateinit var etNumber: EditText
    private lateinit var txtResult: TextView
    private lateinit var btnFactorial: Button
    private lateinit var btnPrime: Button
    private lateinit var btnCalculator: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.factorial)

        etNumber = findViewById(R.id.etNumber)
        txtResult = findViewById(R.id.txtResult)

        btnFactorial = findViewById(R.id.btnFactorial)
        btnPrime = findViewById(R.id.btnPrime)
        btnCalculator = findViewById(R.id.btnCalculator)

        btnFactorial.setOnClickListener {

            val numberText = etNumber.text.toString()

            if (numberText.isEmpty()) {
                txtResult.text = "Enter a number"
                return@setOnClickListener
            }

            val number = numberText.toInt()

            var factorial = 1L

            for (i in 1..number) {
                factorial *= i
            }

            txtResult.text = "Factorial = $factorial"
        }

        // Open Prime Page
        btnPrime.setOnClickListener {

            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }

        // Open Calculator Page
        btnCalculator.setOnClickListener {

            startActivity(
                Intent(this, CalculatorActivity::class.java)
            )
        }
    }
}