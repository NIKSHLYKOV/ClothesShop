package ru.nikshlykov.clothesshop.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nikshlykov.clothesshop.data.models.ClothesCategory
import ru.nikshlykov.clothesshop.data.repositories.ClothesCategoriesRepository

class ClothesCategoriesViewModel(private val clothesCategoriesRepository: ClothesCategoriesRepository) :
    ViewModel() {

    private val _categories = MutableLiveData<ArrayList<ClothesCategory>>()
    val categories: LiveData<ArrayList<ClothesCategory>> = _categories

    fun categoriesRequest(){
        _categories.value = clothesCategoriesRepository.getTestClothesCategories()
    }
}