package co.tiagoaguiar.fitnesstracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

        private lateinit var btnImc : LinearLayout
        private lateinit var btnCalories : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnImc = findViewById(R.id.btn_imc)
        btnCalories = findViewById(R.id.btn_calories)

        btnImc.setOnClickListener {
            val i = Intent(this, ImcActivity::class.java)

            startActivity(i)
        }

        btnCalories.setOnClickListener {
            val i = Intent(this, CaloriesActivity::class.java)
            startActivity(i)
        }
    }
}