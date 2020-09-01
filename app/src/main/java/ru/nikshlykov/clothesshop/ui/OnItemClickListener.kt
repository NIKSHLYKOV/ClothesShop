package ru.nikshlykov.clothesshop.ui

import android.view.View

interface OnItemClickListener<T> {
    fun onItemClick(model: T, v: View)
}