package com.example.e_commerce.data

import com.example.e_commerce.data.model.LoginRequest
import com.example.e_commerce.data.model.LoginResponse
import com.example.e_commerce.models.CartModel
import com.example.e_commerce.models.CartPostModel
import com.example.e_commerce.models.ProductModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("products")
    suspend fun getAllProducts(): List<ProductModel>

    @GET("products/categories")
    suspend fun getCategories(): List<String>

    @POST("auth/login")
    suspend fun login(@Body credentials: LoginRequest): LoginResponse

    @GET("carts/user/{userId}")
    suspend fun getCartByUserId(@Path("userId") userId: Int): List<CartModel>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductModel

    @POST("carts")
    suspend fun addToCart(@Body cart: CartPostModel): CartModel

}


annotation class GET