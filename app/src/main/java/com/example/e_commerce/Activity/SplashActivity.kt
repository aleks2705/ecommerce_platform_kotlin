package com.example.e_commerce.Activity

import android.app.Activity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.e_commerce.R
import com.example.e_commerce.databinding.ActivityStartBinding

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {  }

    }
}