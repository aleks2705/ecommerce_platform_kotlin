package com.example.e_commerce.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.e_commerce.ProductAdapter
import com.example.e_commerce.databinding.ActivityMainBinding
import com.example.e_commerce.models.CategoryModel
import com.example.e_commerce.repository.MainRepository
import com.example.e_commerce.ui.products.CategoryAdapter
import com.example.e_commerce.ui.products.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductAdapter
    private val repository = MainRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Banner.setImageResource(com.example.e_commerce.R.drawable.bannermain)

        binding.progressBarBanner.visibility = View.GONE
        binding.progressBar2.visibility = View.VISIBLE
        binding.progressBar3.visibility = View.VISIBLE

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        productAdapter = ProductAdapter(mutableListOf())
        binding.recyclerViewPop.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerViewPop.adapter = productAdapter

        binding.recyclerViewCat.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        viewModel.filteredProducts.observe(this) { list ->
            productAdapter.updateList(list)
        }

        binding.editTextText.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchProducts(s.toString())
            }
            override fun afterTextChanged(s: android.text.Editable?) {}
        })

        binding.profileLayout.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        binding.mainLayout.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.cartLayout.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            try {
                val products = repository.getAllProducts()
                productAdapter.updateList(products)
                binding.progressBar3.visibility = View.GONE
            } catch (e: Exception) {
                Log.e("PRODUCTS", "Erreur de chargement", e)
                Toast.makeText(this@MainActivity, "Erreur lors du chargement", Toast.LENGTH_SHORT).show()
            }

            try {
                val categories = repository.getCategories()
                    .mapIndexed { index, name -> CategoryModel(id = index, name = name) }
                    .toMutableList()

                binding.recyclerViewCat.adapter = CategoryAdapter(categories) { selectedCategory ->
                    val intent = Intent(this@MainActivity, CategoryListActivity::class.java)
                    intent.putExtra("category", selectedCategory.name)
                    startActivity(intent)
                }
                binding.progressBar2.visibility = View.GONE
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Erreur chargement catégories", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
