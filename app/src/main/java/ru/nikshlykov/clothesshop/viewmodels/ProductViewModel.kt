package ru.nikshlykov.clothesshop.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nikshlykov.clothesshop.data.models.Product
import ru.nikshlykov.clothesshop.data.repositories.ProductRepository

class ProductViewModel constructor(private var productRepository: ProductRepository) : ViewModel() {
    private val _product = MutableLiveData<Product?>()
    val product: LiveData<Product?> = _product

    fun productRequest(productId: Long) {
        _product.value = productRepository.getProduct(productId)
    }
}