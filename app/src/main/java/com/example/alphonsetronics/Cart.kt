package com.example.alphonsetronics

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Cart : AppCompatActivity() {

    private lateinit var navHome: LinearLayout
    private lateinit var navCart: LinearLayout
    private lateinit var navLiked: LinearLayout
    private lateinit var navSettings: LinearLayout

    private lateinit var rvCartItems: RecyclerView
    private lateinit var layoutEmpty: LinearLayout
    private lateinit var tvSubtotal: TextView
    private lateinit var tvTotal: TextView
    private lateinit var btnCheckout: Button
    private lateinit var orderSummaryCard: CardView

    private lateinit var adapter: CartItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        navHome = findViewById(R.id.navHome)
        navCart = findViewById(R.id.navCart)
        navLiked = findViewById(R.id.navLiked)
        navSettings = findViewById(R.id.navSettings)
        rvCartItems = findViewById(R.id.rvCartItems)
        layoutEmpty = findViewById(R.id.layoutEmpty)
        tvSubtotal = findViewById(R.id.tvSubtotal)
        tvTotal = findViewById(R.id.tvTotal)
        btnCheckout = findViewById(R.id.btnCheckout)
        orderSummaryCard = findViewById(R.id.orderSummaryCard)

        val cartItems = CartManager.getAll().toMutableList()
        adapter = CartItemsAdapter(
            cartItems,
            onRemove = { item ->
                CartManager.remove(item.productId)
                adapter.removeItem(item)
                Toast.makeText(this, "${item.name} removed from cart.", Toast.LENGTH_SHORT).show()
                updateTotal()
                updateEmptyState()
            },
            onQuantityChanged = {
                updateTotal()
            }
        )

        rvCartItems.layoutManager = LinearLayoutManager(this)
        rvCartItems.adapter = adapter

        updateTotal()
        updateEmptyState()

        btnCheckout.setOnClickListener {
            if (CartManager.getAll().isEmpty()) {
                Toast.makeText(this, "Your cart is empty!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Proceeding to checkout...", Toast.LENGTH_SHORT).show()
                // TODO: Navigate to Checkout screen
            }
        }

        navHome.setOnClickListener {
            startActivity(Intent(this, Dashboard::class.java))
        }

        navCart.setOnClickListener {
            Toast.makeText(this, "You are in My Cart", Toast.LENGTH_SHORT).show()
        }

        navLiked.setOnClickListener {
            startActivity(Intent(this, Liked::class.java))
        }

        navSettings.setOnClickListener {
            startActivity(Intent(this, Appsettings::class.java))
        }
    }

    private fun updateTotal() {
        val total = CartManager.getTotal()
        val formatted = "₱%,d".format(total)
        tvSubtotal.text = formatted
        tvTotal.text = formatted
    }

    private fun updateEmptyState() {
        if (CartManager.getAll().isEmpty()) {
            rvCartItems.visibility = View.GONE
            layoutEmpty.visibility = View.VISIBLE
            orderSummaryCard.visibility = View.GONE
            btnCheckout.visibility = View.GONE
        } else {
            rvCartItems.visibility = View.VISIBLE
            layoutEmpty.visibility = View.GONE
            orderSummaryCard.visibility = View.VISIBLE
            btnCheckout.visibility = View.VISIBLE
        }
    }
}
