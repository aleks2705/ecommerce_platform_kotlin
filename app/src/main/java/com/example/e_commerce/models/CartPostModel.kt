package com.example.e_commerce.models

data class CartPostModel(
    val userId: Int,
    val date: String,
    val products: List<CartProduct>
)

