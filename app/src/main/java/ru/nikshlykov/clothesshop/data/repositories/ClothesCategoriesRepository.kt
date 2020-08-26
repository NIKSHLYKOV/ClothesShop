package ru.nikshlykov.clothesshop.data.repositories

import ru.nikshlykov.clothesshop.data.models.ClothesCategory
import kotlin.collections.ArrayList

class ClothesCategoriesRepository {
    fun getTestClothesCategories(): ArrayList<ClothesCategory> {
        val categoriesList: ArrayList<ClothesCategory> = ArrayList()
        categoriesList.add(ClothesCategory(1L, "Брюки"))
        categoriesList.add(ClothesCategory(2L, "Худи и свитшоты"))
        categoriesList.add(ClothesCategory(3L, "Рубашки"))
        categoriesList.add(ClothesCategory(4L, "Куртки"))
        categoriesList.add(ClothesCategory(5L, "Футболки"))
        categoriesList.add(ClothesCategory(6L, "Джинсы"))
        categoriesList.add(ClothesCategory(7L, "Нижнее бельё"))
        categoriesList.add(ClothesCategory(8L, "Аксессуары"))
        return categoriesList
    }
}