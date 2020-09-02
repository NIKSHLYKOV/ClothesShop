package ru.nikshlykov.clothesshop.data.models

data class Product(
    val id: Long,
    val name: String,
    val price: Int,
    val size: String,
    val color: String,
    val material: String,
    val description: String
)