package com.example.e_commerce.ui.login

import androidx.annotation.StringRes


data class LoginResult(
     val success: LoggedInUserView? = null,
     @StringRes val error: Int? = null
)
