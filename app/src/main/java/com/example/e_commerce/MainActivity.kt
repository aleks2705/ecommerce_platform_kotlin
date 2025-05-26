package com.example.e_commerce

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.repository.MainRepository
import com.example.e_commerce.ui.products.CategoryAdapter
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val repository = MainRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val banner = findViewById<ShapeableImageView>(R.id.Banner)
        banner.setImageResource(R.drawable.bannermain)
        val progressBar = findViewById<ProgressBar>(R.id.progressBarBanner)
        progressBar.visibility = View.GONE
        val categoryRecycler = findViewById<RecyclerView>(R.id.recyclerViewCat)
        categoryRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerViewPop)
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            try {
                val products = repository.getAllProducts()
                recyclerView.adapter = ProductAdapter(products)
            } catch (e: Exception) {
                Log.e("PRODUCTS", "Erreur de chargement", e)
                Toast.makeText(this@MainActivity, "Erreur lors du chargement", Toast.LENGTH_SHORT).show()
            }
            try {
                val categories = repository.getCategories()
                categoryRecycler.adapter = CategoryAdapter(categories)
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Erreur chargement catégories", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
