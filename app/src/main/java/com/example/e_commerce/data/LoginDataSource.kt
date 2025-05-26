package com.example.e_commerce.data

import com.example.e_commerce.data.model.LoginRequest
import com.example.e_commerce.data.model.LoginResponse
import java.io.IOException

class LoginDataSource {

    suspend fun login(username: String, password: String): Result<LoginResponse> {
        return try {
            val request = LoginRequest(username, password)
            val response = RetrofitInstance.api.login(request)
            Result.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(IOException("Erreur de connexion", e))
        }
    }

    fun logout() {
    }
}
