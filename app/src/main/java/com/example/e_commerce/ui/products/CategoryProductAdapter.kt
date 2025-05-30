package com.example.e_commerce.ui.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerce.R
import com.example.e_commerce.models.ProductModel

class CategoryProductAdapter(private val items: List<ProductModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_LEFT = 0
        private const val VIEW_TYPE_RIGHT = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) VIEW_TYPE_LEFT else VIEW_TYPE_RIGHT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_LEFT) {
            val view = inflater.inflate(R.layout.viewholder_item_pic_left, parent, false)
            LeftViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.viewholder_item_pic_right, parent, false)
            RightViewHolder(view)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is LeftViewHolder) {
            holder.bind(item)
        } else if (holder is RightViewHolder) {
            holder.bind(item)
        }
    }

    class LeftViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: ProductModel) {
            itemView.findViewById<TextView>(R.id.titleTxt).text = product.title
            itemView.findViewById<TextView>(R.id.priceTxt).text = "$" + product.price
            itemView.findViewById<RatingBar>(R.id.ratingBar).rating = product.rating.rate.toFloat()
            Glide.with(itemView.context).load(product.image).into(itemView.findViewById(R.id.imageView8))
        }
    }

    class RightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: ProductModel) {
            itemView.findViewById<TextView>(R.id.titleTxt).text = product.title
            itemView.findViewById<TextView>(R.id.priceTxt).text = "$" + product.price
            itemView.findViewById<RatingBar>(R.id.ratingBar).rating = product.rating.rate.toFloat()
            Glide.with(itemView.context).load(product.image).into(itemView.findViewById(R.id.imageView8))
        }
    }
}
