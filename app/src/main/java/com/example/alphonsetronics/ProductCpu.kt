package com.example.alphonsetronics

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProductCpu : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var btnLiked: ImageButton
    private lateinit var btnAddToCart: Button
    private var isLiked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product_cpu)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnBack = findViewById(R.id.btnBack)
        btnLiked = findViewById(R.id.btnLiked)
        btnAddToCart = findViewById(R.id.btnAddToCart)

        btnBack.setOnClickListener {
            finish()
        }

        btnLiked.setOnClickListener {
            isLiked = !isLiked
            if (isLiked) {
                btnLiked.setImageResource(android.R.drawable.btn_star_big_on)
                Toast.makeText(this, "Added to Liked Products!", Toast.LENGTH_SHORT).show()
            } else {
                btnLiked.setImageResource(android.R.drawable.btn_star_big_off)
                Toast.makeText(this, "Removed from Liked Products.", Toast.LENGTH_SHORT).show()
            }
        }

        btnAddToCart.setOnClickListener {
            Toast.makeText(this, "AMD Ryzen 5 7600 added to cart!", Toast.LENGTH_SHORT).show()
        }
    }
}