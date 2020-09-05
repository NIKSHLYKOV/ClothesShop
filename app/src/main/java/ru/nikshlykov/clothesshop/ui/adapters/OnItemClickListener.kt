package ru.nikshlykov.clothesshop.ui.adapters

import android.view.View

interface OnItemClickListener<T> {
    fun onItemClick(model: T, v: View)
}