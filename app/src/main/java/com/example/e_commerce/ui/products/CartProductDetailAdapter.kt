package com.example.e_commerce.ui.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.R
import com.example.e_commerce.models.CartProductDetail

class CartProductDetailAdapter(private val items: List<CartProductDetail>) :
    RecyclerView.Adapter<CartProductDetailAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.product_name)
        val quantityText: TextView = itemView.findViewById(R.id.product_quantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_product_detail_cart, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.nameText.text = item.name
        holder.quantityText.text = "Quantité : ${item.quantity}"
    }

    override fun getItemCount(): Int = items.size
}