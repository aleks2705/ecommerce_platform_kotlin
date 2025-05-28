package com.example.e_commerce.ui.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.R
import com.example.e_commerce.models.CategoryModel

class CategoryAdapter(
    private val items: List<CategoryModel>,
    private val onItemClick: (CategoryModel) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var selectedPosition = -1

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.titleCat)
        val container: LinearLayout = itemView.findViewById(R.id.categoryContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.name

        holder.container.isSelected = position == selectedPosition

        val colorRes = if (position == selectedPosition) R.color.white else R.color.darkBrown
        holder.name.setTextColor(ContextCompat.getColor(holder.itemView.context, colorRes))

        holder.itemView.setOnClickListener {
            val prevPos = selectedPosition
            selectedPosition = holder.adapterPosition
            notifyItemChanged(prevPos)
            notifyItemChanged(selectedPosition)
            onItemClick(items[selectedPosition])
        }
    }

    override fun getItemCount() = items.size
}
