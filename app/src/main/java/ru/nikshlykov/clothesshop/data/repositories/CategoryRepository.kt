package ru.nikshlykov.clothesshop.data.repositories

import ru.nikshlykov.clothesshop.data.models.Product

class CategoryRepository {
    fun getTestProducts(): ArrayList<Product> {
        val productsList: ArrayList<Product> = ArrayList()
        productsList.add(Product(1L, "Джинсы зауженные", 1000, "M", "red", "something", "............"))
        productsList.add(Product(1L, "Джинсы ляля", 2000, "M", "red", "something", "............"))
        productsList.add(Product(1L, "Синие джинсы", 1090, "M", "red", "something", "............"))
        productsList.add(Product(1L, "Пацанские джинсы", 3000, "M", "red", "something", "............"))
        productsList.add(Product(1L, "Джинсы", 1500, "M", "red", "something", "............"))
        return productsList
    }
}