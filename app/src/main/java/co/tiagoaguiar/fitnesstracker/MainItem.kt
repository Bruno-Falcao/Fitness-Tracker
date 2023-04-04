package co.tiagoaguiar.fitnesstracker

import android.graphics.drawable.AdaptiveIconDrawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MainItem(
    val id: Int,
    @DrawableRes val drawable: Int,
    @StringRes val textStringId: Int,
    val color: Int
)
