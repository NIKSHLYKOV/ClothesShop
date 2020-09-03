package ru.nikshlykov.clothesshop.data.repositories

import ru.nikshlykov.clothesshop.data.models.Product

class ProductRepository {
    fun getProduct(productId: Long): Product? {
        return when (productId) {
            1L -> Product(1L, "Джинсы зауженные", 1000, "M", "red", "something", "....")
            2L -> Product(2L, "Джинсы ляля", 2000, "M", "red", "something", ".........")
            3L -> Product(3L, "Синие джинсы", 1090, "M", "red", "something", "........")
            4L -> Product(4L, "Пацанские джинсы", 3000, "M", "red", "something", "....")
            5L -> Product(5L, "Джинсы", 1500, "M", "red", "something", "............")
            else -> null
        }
    }
}