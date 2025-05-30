package com.example.e_commerce.Activity

import android.content.Intent
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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.ProductAdapter
import com.example.e_commerce.R
import com.example.e_commerce.models.CategoryModel
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
        val progressBarCat = findViewById<ProgressBar>(R.id.progressBar2)
        val progressBarProd = findViewById<ProgressBar>(R.id.progressBar3)
        progressBar.visibility = View.GONE
        progressBarCat.visibility = View.VISIBLE
        progressBarProd.visibility = View.VISIBLE
        val categoryRecycler = findViewById<RecyclerView>(R.id.recyclerViewCat)
        categoryRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerViewPop)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        lifecycleScope.launch {
            try {
                val products = repository.getAllProducts()
                recyclerView.adapter = ProductAdapter(products.toMutableList())
                progressBarProd.visibility = View.GONE
            } catch (e: Exception) {
                Log.e("PRODUCTS", "Erreur de chargement", e)
                Toast.makeText(this@MainActivity, "Erreur lors du chargement", Toast.LENGTH_SHORT).show()
            }
            try {
                val categoryModels = repository.getCategories()
                    .mapIndexed { index, name -> CategoryModel(id = index, name = name) }
                    .toMutableList()

                categoryRecycler.adapter = CategoryAdapter(categoryModels) { selectedCategory ->
                    val intent = Intent(this@MainActivity, CategoryListActivity::class.java)
                    intent.putExtra("category", selectedCategory.name)
                    startActivity(intent)
                }
                progressBarCat.visibility = View.GONE
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Erreur chargement catégories", Toast.LENGTH_SHORT).show()
            }
        }
    }

}