package com.example.e_commerce.repository

import com.example.e_commerce.data.RetrofitInstance
import com.example.e_commerce.models.ProductModel

class MainRepository {
    suspend fun getAllProducts(): List<ProductModel> {
        return RetrofitInstance.api.getAllProducts()
    }

    suspend fun getCategories(): List<String> {
        return RetrofitInstance.api.getCategories()
    }
}
//Va chercher les données via l'API en appelant retrofit
