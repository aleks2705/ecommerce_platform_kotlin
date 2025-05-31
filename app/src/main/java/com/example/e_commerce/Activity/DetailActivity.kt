package com.example.e_commerce.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.e_commerce.R
import com.example.e_commerce.databinding.ActivityDetailBinding
import com.example.e_commerce.models.CartProduct
import com.example.e_commerce.models.ProductModel

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    private lateinit var item: ProductModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bundle()
        initSizeList()


    }

    private fun initSizeList() {
        binding.apply {
            SBtn.setOnClickListener {
                SBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
                MBtn.setBackgroundResource(0)
                LBtn.setBackgroundResource(0)
                XLBtn.setBackgroundResource(0)
            }
            MBtn.setOnClickListener {
                SBtn.setBackgroundResource(0)
                MBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
                LBtn.setBackgroundResource(0)
                XLBtn.setBackgroundResource(0)
            }
            LBtn.setOnClickListener {
                SBtn.setBackgroundResource(0)
                MBtn.setBackgroundResource(0)
                LBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
                XLBtn.setBackgroundResource(0)
            }
            XLBtn.setOnClickListener {
                SBtn.setBackgroundResource(0)
                MBtn.setBackgroundResource(0)
                LBtn.setBackgroundResource(0)
                XLBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
            }
        }
    }

    private fun bundle(){
        binding.apply {
            item=intent.getSerializableExtra("object") as ProductModel

            if ((item.category == "men's clothing" || item.category == "women's clothing") && item.id != 1) {
                binding.sizeLayout.visibility = View.VISIBLE
            } else {
                binding.sizeLayout.visibility = View.GONE
            }
            Glide.with(this@DetailActivity)
                .load(item.image)
                .into(binding.picMain)

            titleTxt.text=item.title
            descriptionTxt.text=item.description
            priceTxtDetail.text="$"+item.price
            ratingtxt.text=item.rating.toString()

            AddToCartBtn.setOnClickListener {
                val quantity = numberQty.text.toString().toInt()

                val productInCart = CartProduct(
                    productId = item.id,
                    quantity = quantity
                )

            }
            backBtn.setOnClickListener {
                startActivity(Intent(this@DetailActivity, MainActivity::class.java))
            }
            plusCart.setOnClickListener {
                val currentQty = numberQty.text.toString().toIntOrNull() ?: 0
                numberQty.text = (currentQty + 1).toString()
            }

            minusCart.setOnClickListener {
                val currentQty = numberQty.text.toString().toIntOrNull() ?: 0
                if (currentQty>0) {
                    numberQty.text = (currentQty - 1).toString()
                }
            }
        }
    }
}