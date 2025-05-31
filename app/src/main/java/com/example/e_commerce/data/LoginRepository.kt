package com.example.e_commerce.data

import com.example.e_commerce.data.model.LoginResponse
import com.example.e_commerce.data.RetrofitInstance
import com.example.e_commerce.models.UserModel

class LoginRepository(private val dataSource: LoginDataSource) {

    companion object {
        var currentUserId: Int? = null
    }

    suspend fun login(username: String, password: String): Result<LoginResponse> {
        val result = dataSource.login(username, password)

        if (result is Result.Success) {
            try {
                val users: List<UserModel> = RetrofitInstance.api.getAllUsers()
                val matchedUser = users.find { user -> user.username == username }

                if (matchedUser != null) {
                    currentUserId = matchedUser.id
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return result
    }
}
