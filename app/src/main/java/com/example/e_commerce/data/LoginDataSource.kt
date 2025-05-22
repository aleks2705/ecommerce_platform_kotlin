package com.example.e_commerce.data

import com.example.e_commerce.data.model.LoggedInUser
import org.json.JSONArray
import java.io.IOException
import java.net.URL
import android.util.Log

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        return try {
            val json = URL("https://fakestoreapi.com/users").readText()
            val users = JSONArray(json)
            Log.d("USERS", users.toString())

            for (i in 0 until users.length()) {
                val user = users.getJSONObject(i)
                if (user.getString("username") == username &&
                    user.getString("password") == password) {

                    val loggedInUser = LoggedInUser(
                        user.getString("id"),
                        user.getString("username")
                    )
                    return Result.Success(loggedInUser)
                }
            }
            Result.Error(Exception("Utilisateur non trouvé"))
        } catch (e: Exception) {
            Log.e("LoginError", "Erreur pendant la connexion", e)
            Result.Error(Exception("Connexion échouée"))
        }}
    fun logout() {}

}