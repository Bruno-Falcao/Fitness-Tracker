package co.tiagoaguiar.fitnesstracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes

class BmiActivity : AppCompatActivity() {

    private lateinit var editWeight: EditText
    private lateinit var editHeight: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        editWeight = findViewById(R.id.edit_imc_weight)
        editHeight = findViewById(R.id.edit_imc_height)

        val btnSend: Button = findViewById(R.id.btn_imc_send)

        btnSend.setOnClickListener {
            if (!validate()) {
                Toast.makeText(this, R.string.fields_message, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val weight = editWeight.text.toString().toInt()
            val height = editHeight.text.toString().toInt()

            val result = calculateBmi(weight, height)
            Log.d("Teste", "resultado: $result")

            val bmiResponseId = bmiResponse(result)
            Toast.makeText(this, bmiResponseId, Toast.LENGTH_SHORT).show()
        }
    }

    @StringRes
    private fun bmiResponse(bmi: Double): Int {
        return when {
            bmi < 15.0 -> R.string.imc_severely_low_weight
            bmi < 16.0 -> R.string.imc_very_low_weight
            bmi < 18.5 -> R.string.imc_low_weight
            bmi < 25.0 -> R.string.normal
            bmi < 30.0 -> R.string.imc_high_weight
            bmi < 35.0 -> R.string.imc_so_high_weight
            bmi < 40.0 -> R.string.imc_severely_high_weight
            else -> R.string.imc_extreme_weight
        }
    }


    private fun validate(): Boolean {
        return (editWeight.text.toString().isNotEmpty()
                && editHeight.text.toString().isNotEmpty()
                && !editWeight.text.toString().startsWith("0")
                && !editHeight.text.toString().startsWith("0"))
    }

    private fun calculateBmi(weight: Int, height: Int): Double {
        return weight / ((height / 100.0) * (height / 100.0))

    }
}