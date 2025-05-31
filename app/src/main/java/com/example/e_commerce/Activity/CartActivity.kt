package com.example.e_commerce.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerce.R
import com.example.e_commerce.data.LoginRepository
import com.example.e_commerce.data.RetrofitInstance
import com.example.e_commerce.databinding.ActivityCartBinding
import com.example.e_commerce.models.CartProductDetail
import com.example.e_commerce.ui.cart.CartViewModel
import com.example.e_commerce.ui.products.CartProductDetailAdapter
import kotlinx.coroutines.launch

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private val cartViewModel: CartViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val backBtn = findViewById<LinearLayout>(R.id.backBtn)
        backBtn.setOnClickListener {
            startActivity(Intent(this@CartActivity, MainActivity::class.java))
        }

        binding.cartView.layoutManager = LinearLayoutManager(this)

        val userId = LoginRepository.currentUserId
        if (userId != null) {
            cartViewModel.carts.observe(this) { carts ->
                if (carts.isNotEmpty()) {
                    val cart = carts.first()
                    val productDetails = mutableListOf<CartProductDetail>()

                    lifecycleScope.launch {
                        cart.products.forEach { item ->
                            val product = RetrofitInstance.api.getProductById(item.productId)
                            productDetails.add(
                                CartProductDetail(
                                    name = product.title,
                                    quantity = item.quantity,
                                    price = product.price
                                )
                            )
                        }
                        binding.cartView.adapter = CartProductDetailAdapter(productDetails)
                    }
                }
            }
            cartViewModel.fetchCartByUser(userId)

        }
    }
}