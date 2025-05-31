package com.example.e_commerce.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.e_commerce.Activity.CartActivity
import com.example.e_commerce.R
import com.example.e_commerce.data.LoginRepository
import com.example.e_commerce.data.RetrofitInstance
import com.example.e_commerce.databinding.ActivityProfileBinding
import com.example.e_commerce.ui.login.LoginActivity
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val backBtn = findViewById<LinearLayout>(R.id.backBtn)
        backBtn.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, MainActivity::class.java))
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.profileScroll) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userId = LoginRepository.currentUserId
        if (userId == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        lifecycleScope.launch {
            try {
                val users = RetrofitInstance.api.getAllUsers()
                val user = users.find { it.id == userId }

                if (user != null) {
                    binding.name.text ="${user.name.firstname.capitalize()} ${user.name.lastname.capitalize()}"
                    binding.email.text = user.email
                    binding.phone.text = user.phone
                    binding.address.text =
                        "${user.address.number} ${user.address.street}, ${user.address.city}, ${user.address.zipcode}"
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.logoutBtn.setOnClickListener {
            LoginRepository.currentUserId = null
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
