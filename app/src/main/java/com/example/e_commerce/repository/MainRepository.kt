package com.example.e_commerce.repository

import com.example.e_commerce.data.RetrofitInstance
import com.example.e_commerce.models.CartModel
import com.example.e_commerce.models.CartPostModel
import com.example.e_commerce.models.ProductModel

class MainRepository {
    suspend fun getAllProducts(): List<ProductModel> {
        return RetrofitInstance.api.getAllProducts()
    }

    suspend fun getCategories(): List<String> {
        return RetrofitInstance.api.getCategories()
    }

    suspend fun getCartByUserId(userId: Int): List<CartModel>{
        return RetrofitInstance.api.getCartByUserId(userId)
    }

    suspend fun getProductById(id: Int): ProductModel{
        return RetrofitInstance.api.getProductById(id)
    }

    suspend fun addToCart(cart: CartPostModel): CartModel{
        return RetrofitInstance.api.addToCart(cart)
    }
}
//Va chercher les données via l'API en appelant retrofit
