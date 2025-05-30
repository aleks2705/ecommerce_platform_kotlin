package com.example.e_commerce.models

data class CartModel(
    val id: Int,
    val userId: Int,
    val date: String,
    val products: List<CartProduct>
)
