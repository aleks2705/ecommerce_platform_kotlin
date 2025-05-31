package com.example.e_commerce.models

data class UserModel(
    val id: Int,
    val email: String,
    val username: String,
    val password: String,
    val name: Name,
    val address: Address,
    val phone: String
)

data class Name(
    val firstname: String,
    val lastname: String
)

data class Address(
    val geolocation: GeoLocation,
    val city: String,
    val street: String,
    val number: Int,
    val zipcode: String
)

data class GeoLocation(
    val lat: String,
    val long: String
)
