package com.example.alphonsetronics

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Liked : AppCompatActivity() {

    private lateinit var navHome: LinearLayout
    private lateinit var navCart: LinearLayout
    private lateinit var navLiked: LinearLayout
    private lateinit var navSettings: LinearLayout
    private lateinit var rvLikedItems: RecyclerView
    private lateinit var layoutEmpty: LinearLayout

    private lateinit var adapter: LikedItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liked)

        navHome = findViewById(R.id.navHome)
        navCart = findViewById(R.id.navCart)
        navLiked = findViewById(R.id.navLiked)
        navSettings = findViewById(R.id.navSettings)
        rvLikedItems = findViewById(R.id.rvLikedItems)
        layoutEmpty = findViewById(R.id.layoutEmpty)

        // Set up RecyclerView
        val likedItems = LikedManager.getAll().toMutableList()
        adapter = LikedItemsAdapter(likedItems) { item ->
            LikedManager.remove(item.productId)
            adapter.removeItem(item)
            Toast.makeText(this, "${item.name} removed from liked.", Toast.LENGTH_SHORT).show()
            updateEmptyState()
        }
        rvLikedItems.layoutManager = LinearLayoutManager(this)
        rvLikedItems.adapter = adapter

        updateEmptyState()

        navHome.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        navCart.setOnClickListener {
            val intent = Intent(this, Cart::class.java)
            startActivity(intent)
        }

        navLiked.setOnClickListener {
            Toast.makeText(this, "Liked Products", Toast.LENGTH_SHORT).show()
        }

        navSettings.setOnClickListener {
            val intent = Intent(this, Appsettings::class.java)
            startActivity(intent)
        }
    }

    private fun updateEmptyState() {
        if (LikedManager.getAll().isEmpty()) {
            layoutEmpty.visibility = View.VISIBLE
            rvLikedItems.visibility = View.GONE
        } else {
            layoutEmpty.visibility = View.GONE
            rvLikedItems.visibility = View.VISIBLE
        }
    }
}