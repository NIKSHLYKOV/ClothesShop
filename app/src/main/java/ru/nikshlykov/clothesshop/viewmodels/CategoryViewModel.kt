package ru.nikshlykov.clothesshop.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nikshlykov.clothesshop.data.models.Product
import ru.nikshlykov.clothesshop.data.repositories.CategoryRepository

class CategoryViewModel constructor(private val categoryRepository: CategoryRepository) :
    ViewModel() {
    private val _products = MutableLiveData<ArrayList<Product>>()
    val product: LiveData<ArrayList<Product>> = _products

    fun productsRequest() {
        _products.value = categoryRepository.getTestProducts()
    }
}