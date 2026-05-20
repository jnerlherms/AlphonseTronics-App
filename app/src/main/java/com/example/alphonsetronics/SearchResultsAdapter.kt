package com.example.alphonsetronics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SearchResultsAdapter(
    private val results: MutableList<Product>,
    private val onClick: (Product) -> Unit
) : RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    data class Product(
        val productId: String,
        val name: String,
        val price: String,
        val imageResId: Int
    )

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgProduct: ImageView = view.findViewById(R.id.imgSearchProduct)
        val tvName: TextView = view.findViewById(R.id.tvSearchProductName)
        val tvPrice: TextView = view.findViewById(R.id.tvSearchProductPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_result, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = results[position]
        holder.imgProduct.setImageResource(product.imageResId)
        holder.tvName.text = product.name
        holder.tvPrice.text = product.price
        holder.itemView.setOnClickListener { onClick(product) }
    }

    override fun getItemCount() = results.size

    fun updateResults(newResults: MutableList<Product>) {
        results.clear()
        results.addAll(newResults)
        notifyDataSetChanged()
    }
}
