package com.example.practicacalificada

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextAmount = findViewById<EditText>(R.id.editTextAmount)
        val spinnerCurrencies = findViewById<Spinner>(R.id.spinnerCurrencies)
        val buttonConvert = findViewById<Button>(R.id.buttonConvert)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        // Configuración del Spinner con las divisas
        val currencies = resources.getStringArray(R.array.currencies_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCurrencies.adapter = adapter

        // Tipos de cambio simulados
        val exchangeRates = mapOf(
            "Dólares" to 0.27,
            "Euros" to 0.24,
            "Pesos Mexicanos" to 5.29
        )

        buttonConvert.setOnClickListener {
            val amountInSoles = editTextAmount.text.toString().toDoubleOrNull()

            if (amountInSoles == null || amountInSoles <= 0) {
                Toast.makeText(this, "Ingrese un monto válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedCurrency = spinnerCurrencies.selectedItem.toString()
            val exchangeRate = exchangeRates[selectedCurrency] ?: 0.0
            val convertedAmount = amountInSoles * exchangeRate

            textViewResult.text = "Resultado: %.2f %s".format(convertedAmount, selectedCurrency)
        }
    }
}