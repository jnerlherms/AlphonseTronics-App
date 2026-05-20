package com.example.alphonsetronics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartItemsAdapter(
    private val items: MutableList<CartManager.CartItem>,
    private val onRemove: (CartManager.CartItem) -> Unit,
    private val onQuantityChanged: () -> Unit
) : RecyclerView.Adapter<CartItemsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgProduct: ImageView = view.findViewById(R.id.imgCartProduct)
        val tvName: TextView = view.findViewById(R.id.tvCartProductName)
        val tvPrice: TextView = view.findViewById(R.id.tvCartProductPrice)
        val tvQuantity: TextView = view.findViewById(R.id.tvQuantity)
        val btnIncrease: Button = view.findViewById(R.id.btnIncrease)
        val btnDecrease: Button = view.findViewById(R.id.btnDecrease)
        val btnRemove: ImageButton = view.findViewById(R.id.btnRemoveCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.imgProduct.setImageResource(item.imageResId)
        holder.tvName.text = item.name
        holder.tvPrice.text = item.price
        holder.tvQuantity.text = item.quantity.toString()

        holder.btnIncrease.setOnClickListener {
            item.quantity++
            holder.tvQuantity.text = item.quantity.toString()
            onQuantityChanged()
        }

        holder.btnDecrease.setOnClickListener {
            if (item.quantity > 1) {
                item.quantity--
                holder.tvQuantity.text = item.quantity.toString()
                onQuantityChanged()
            } else {
                onRemove(item)
            }
        }

        holder.btnRemove.setOnClickListener {
            onRemove(item)
        }
    }

    override fun getItemCount() = items.size

    fun removeItem(item: CartManager.CartItem) {
        val index = items.indexOfFirst { it.productId == item.productId }
        if (index != -1) {
            items.removeAt(index)
            notifyItemRemoved(index)
        }
    }
}
