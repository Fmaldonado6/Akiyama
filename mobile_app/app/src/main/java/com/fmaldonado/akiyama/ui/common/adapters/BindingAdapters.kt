package com.fmaldonado.akiyama.ui.common.adapters

import android.graphics.drawable.ColorDrawable
import androidx.databinding.BindingConversion


@BindingConversion
fun convertColorToDrawable(color: Int): ColorDrawable? {
    return if (color != 0) ColorDrawable(color) else null
}