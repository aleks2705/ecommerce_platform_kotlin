package com.example.e_commerce.data

import com.example.e_commerce.data.model.LoginResponse

class LoginRepository(private val dataSource: LoginDataSource) {
    suspend fun login(username: String, password: String): Result<LoginResponse> {
        return dataSource.login(username, password)
    }
}
