package com.example.e_commerce.data

import com.example.e_commerce.data.model.LoginRequest
import com.example.e_commerce.data.model.LoginResponse
import com.example.e_commerce.models.ProductModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("products")
    suspend fun getAllProducts(): List<ProductModel>

    @GET("products/categories")
    suspend fun getCategories(): List<String>

    @POST("auth/login")
    suspend fun login(@Body credentials: LoginRequest): LoginResponse

}


annotation class GET