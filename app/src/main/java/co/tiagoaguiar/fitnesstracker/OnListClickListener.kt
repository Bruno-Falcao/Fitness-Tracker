package co.tiagoaguiar.fitnesstracker

import co.tiagoaguiar.fitnesstracker.model.Calc

interface OnListClickListener {

    fun onClick(id: Int, type: String)
    fun onLongClick(id: Int, calc: Calc)
}
