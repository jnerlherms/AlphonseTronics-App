package com.example.alphonsetronics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LikedItemsAdapter(
    private val items: MutableList<LikedManager.LikedItem>,
    private val onRemove: (LikedManager.LikedItem) -> Unit
) : RecyclerView.Adapter<LikedItemsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgProduct: ImageView = view.findViewById(R.id.imgLikedProduct)
        val tvName: TextView = view.findViewById(R.id.tvLikedProductName)
        val tvPrice: TextView = view.findViewById(R.id.tvLikedProductPrice)
        val btnRemove: ImageButton = view.findViewById(R.id.btnRemoveLiked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_liked_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.imgProduct.setImageResource(item.imageResId)
        holder.tvName.text = item.name
        holder.tvPrice.text = item.price
        holder.btnRemove.setOnClickListener {
            onRemove(item)
        }
    }

    override fun getItemCount() = items.size

    fun removeItem(item: LikedManager.LikedItem) {
        val index = items.indexOfFirst { it.productId == item.productId }
        if (index != -1) {
            items.removeAt(index)
            notifyItemRemoved(index)
        }
    }
}
