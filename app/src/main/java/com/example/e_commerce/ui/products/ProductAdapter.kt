package com.example.e_commerce

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerce.Activity.DetailActivity
import com.example.e_commerce.databinding.ViewholderProductsBinding
import com.example.e_commerce.models.ProductModel
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class ProductAdapter(val items: MutableList<ProductModel>): RecyclerView.Adapter<ProductAdapter.Viewholder>(){
    lateinit var context: Context
    class Viewholder(val binding: ViewholderProductsBinding): RecyclerView.ViewHolder(binding.root)

    fun updateList(newList: List<ProductModel>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.Viewholder {
        context=parent.context
        val binding= ViewholderProductsBinding.inflate(LayoutInflater.from(context),parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: ProductAdapter.Viewholder, position: Int) {
        val product = items[position]

        holder.binding.titleProd.text=items[position].title
        holder.binding.priceTxt.text="$"+items[position].price.toString()

        Glide.with(context)
            .load(items[position].image)
            .into(holder.binding.prodPic)

        try {
            val encoder = BarcodeEncoder()
            val bitmap: Bitmap =
                encoder.encodeBitmap(product.id.toString(), BarcodeFormat.QR_CODE, 100, 100)
            holder.binding.qrCodeImage.setImageBitmap(bitmap)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        holder.itemView.setOnClickListener {
            val intent=Intent(context, DetailActivity::class.java)
            intent.putExtra("object", items[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int=items.size

}