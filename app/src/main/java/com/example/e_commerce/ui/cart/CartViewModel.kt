package com.example.e_commerce.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.models.CartModel
import com.example.e_commerce.repository.MainRepository
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    val carts = MutableLiveData<List<CartModel>>()
    private val repository = MainRepository()

    fun fetchCartByUser(userId: Int) {
        viewModelScope.launch {
            val result = repository.getCartByUserId(userId)
            carts.postValue(result)
        }
    }
}