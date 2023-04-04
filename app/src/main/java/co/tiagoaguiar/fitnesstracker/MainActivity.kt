package co.tiagoaguiar.fitnesstracker

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainItems = mutableListOf<MainItem>()
        mainItems.add(
            MainItem(
                id = 1,
                drawable = R.drawable.baseline_wb_sunny_24,
                textStringId = R.string.label_imc,
                color = Color.GREEN
            )
        )

        val adapter = MainAdapter(mainItems)
        recyclerMain = findViewById(R.id.recycler_main)
        recyclerMain.adapter = adapter
        recyclerMain.layoutManager = LinearLayoutManager(this)

    }

    private inner class MainAdapter(
        private val mainItems: List<MainItem>
        ) : RecyclerView.Adapter<MainViewHolder>() {

        // 1 - Qual é o layout XML da celula especifica (item)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view = layoutInflater.inflate(R.layout.main_item, parent, false)
            return MainViewHolder(view)
        }

        // 2 - Disparado toda vez que houver uma rolagem na tela e for necessário trocar o conteúdo
        // da célula
        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val currentItem = mainItems[position]
            holder.bind(currentItem)
        }

        // 3 - Quantas celulas essa lista tem
        override fun getItemCount(): Int {
            return mainItems.size
        }
    }

    // É a classe de uma celula em si
    private inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(currentItem: MainItem) {
            val buttonTest: Button = itemView.findViewById(R.id.btn_item)
            buttonTest.setText(currentItem.textStringId)
        }
    }
}