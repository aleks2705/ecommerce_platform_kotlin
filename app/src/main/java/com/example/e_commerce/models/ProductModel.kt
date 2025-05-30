package com.example.e_commerce.models

import java.io.Serializable


data class ProductModel(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating
): Serializable

data class Rating(
    val rate: Double,
    val count: Int
): Serializable
