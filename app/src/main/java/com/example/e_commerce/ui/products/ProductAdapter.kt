package com.example.e_commerce

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerce.databinding.ViewholderProductsBinding
import com.example.e_commerce.models.ProductModel

class ProductAdapter(val items: MutableList<ProductModel>): RecyclerView.Adapter<ProductAdapter.Viewholder>(){
    lateinit var context: Context
    class Viewholder(val binding: ViewholderProductsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.Viewholder {
        context=parent.context
        val binding= ViewholderProductsBinding.inflate(LayoutInflater.from(context),parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: ProductAdapter.Viewholder, position: Int) {
        holder.binding.titleProd.text=items[position].title
        holder.binding.priceTxt.text="$"+items[position].price.toString()

        Glide.with(context)
            .load(items[position].image)
            .into(holder.binding.imageView5)
    }

    override fun getItemCount(): Int=items.size

}

//class ProductAdapter(private val products: List<ProductModel>) :
//    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
//
//    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val title: TextView = itemView.findViewById(R.id.product_title)
//        val price: TextView = itemView.findViewById(R.id.product_price)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_product, parent, false)
//        return ProductViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
//        val product = products[position]
//        holder.title.text = product.title
//        holder.price.text = "${product.price} €"
//    }
//
//    override fun getItemCount(): Int = products.size
//}
