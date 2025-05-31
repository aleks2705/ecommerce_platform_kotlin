package com.example.e_commerce.Activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.e_commerce.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val content = intent.getStringExtra("raw_content") ?: "Aucun contenu"
        val resultText = findViewById<TextView>(R.id.resultText)
        resultText.text = content
    }
}
