package com.example.e_commerce.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.R
import com.example.e_commerce.repository.MainRepository
import com.example.e_commerce.ui.products.CategoryProductAdapter


class CategoryListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_category_list)

        val backBtn = findViewById<LinearLayout>(R.id.backBtn)
        backBtn.setOnClickListener {
            startActivity(Intent(this@CategoryListActivity, MainActivity::class.java))
        }

        val category = intent.getStringExtra("category") ?: ""
        val recyclerView = findViewById<RecyclerView>(R.id.cartView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            try {
                val allProducts = MainRepository().getAllProducts()
                val filteredProducts = allProducts.filter { it.category == category }
                val adapter = CategoryProductAdapter(this@CategoryListActivity, filteredProducts)
                recyclerView.adapter = adapter
            } catch (e: Exception) {
                Log.e("CATEGORY", "Erreur chargement produits", e)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}