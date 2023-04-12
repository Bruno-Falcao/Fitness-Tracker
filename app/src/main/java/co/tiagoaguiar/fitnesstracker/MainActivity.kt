package co.tiagoaguiar.fitnesstracker

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.fitnesstracker.model.MainItem

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainItems = mutableListOf<MainItem>()
        mainItems.addAll(
            listOf(
                MainItem(
                    id = 1,
                    drawableId = R.drawable.baseline_wb_sunny_24,
                    textStringId = R.string.label_imc,
                    color = Color.GREEN
                ),
                MainItem(
                    id = 2,
                    drawableId = R.drawable.baseline_sports_martial_arts_24,
                    textStringId = R.string.tmb,
                    color = Color.BLUE
                )
            )
        )

        val adapter = MainAdapter(mainItems) { id ->
            when (id) {
                1 -> {
                    val intent = Intent(this@MainActivity, BmiActivity::class.java)
                    startActivity(intent)
                }
                2 -> {
                    val intent = Intent(this@MainActivity, BmrActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        recyclerMain = findViewById(R.id.recycler_main)
        recyclerMain.adapter = adapter
        recyclerMain.layoutManager = GridLayoutManager(this, 2)

    }

    private inner class MainAdapter(
        private val mainItems: List<MainItem>,
        private val onItemClickListener: (Int) -> Unit
    ) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

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

        // É a classe de uma celula em si
        private inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(currentItem: MainItem) {
                val img: ImageView = itemView.findViewById(R.id.item_img)
                val name: TextView = itemView.findViewById(R.id.item_txt_name)
                val container: LinearLayout = itemView.findViewById(R.id.item_container_bmi)

                img.setImageResource(currentItem.drawableId)
                name.setText(currentItem.textStringId)
                container.setBackgroundColor(currentItem.color)

                container.setOnClickListener {
                    onItemClickListener.invoke(currentItem.id)
                }
            }
        }
    }
}