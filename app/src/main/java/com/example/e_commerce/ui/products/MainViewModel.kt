package com.example.e_commerce.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.models.ProductModel
import com.example.e_commerce.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = MainRepository()

    private val _filteredProducts = MutableLiveData<List<ProductModel>>()
    val filteredProducts: LiveData<List<ProductModel>> = _filteredProducts

    fun searchProducts(query: String) {
        viewModelScope.launch {
            val allProducts = repository.getAllProducts()
            _filteredProducts.value = allProducts.filter {
                it.title.contains(query, ignoreCase = true)
            }
        }
    }
}