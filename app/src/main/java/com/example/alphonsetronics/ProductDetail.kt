package com.example.alphonsetronics

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProductDetail : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var btnLiked: ImageButton
    private lateinit var btnAddToCart: Button
    private lateinit var imgProduct: ImageView
    private lateinit var tvProductName: TextView
    private lateinit var tvPrice: TextView
    private lateinit var tvDescription: TextView

    private var isLiked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product_detail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Bind views
        btnBack = findViewById(R.id.btnBack)
        btnLiked = findViewById(R.id.btnLiked)
        btnAddToCart = findViewById(R.id.btnAddToCart)
        imgProduct = findViewById(R.id.imgProduct)
        tvProductName = findViewById(R.id.tvProductName)
        tvPrice = findViewById(R.id.tvPrice)
        tvDescription = findViewById(R.id.tvDescription)

        // Get the product ID passed from Dashboard
        val productId = intent.getStringExtra("PRODUCT_ID") ?: "cpu"

        // Load product data based on which item was clicked
        if (productId == "cpu") {
            imgProduct.setImageResource(R.drawable.cpu)
            tvProductName.text = "AMD Ryzen 5 7600 Desktop Processor"
            tvPrice.text = "₱32,999"
            tvDescription.text = "The AMD Ryzen 5 7600 is a 6-core, 12-thread desktop processor built on the Zen 4 architecture with Socket AM5 compatibility. It features a base clock of 3.8GHz and a boost clock of up to 5.1GHz, making it an excellent choice for gaming and everyday computing tasks. Supports DDR5 memory and PCIe 5.0 for a future-ready platform."

        } else if (productId == "gpu") {
            imgProduct.setImageResource(R.drawable.gpu)
            tvProductName.text = "Colorful GeForce RTX 5060 Battle AX DUO 8GB GDDR7"
            tvPrice.text = "₱89,999"
            tvDescription.text = "The Colorful GeForce RTX 5060 Battle AX DUO features 8GB of next-gen GDDR7 memory and is powered by NVIDIA's latest Blackwell architecture. It delivers exceptional 1080p and 1440p gaming performance with support for DLSS 4, ray tracing, and AI-powered frame generation. Dual-fan cooling keeps thermals low even under heavy load."

        } else if (productId == "ram") {
            imgProduct.setImageResource(R.drawable.ram)
            tvProductName.text = "Corsair Vengeance 32GB DDR5 RAM"
            tvPrice.text = "₱9,499"
            tvDescription.text = "The Corsair Vengeance DDR5 32GB (2x16GB) kit operates at 5600MHz with low-latency timings, delivering blazing-fast performance for gaming, content creation, and multitasking. Built with a sleek black aluminum heat spreader for improved thermal performance. Compatible with Intel and AMD DDR5 platforms including AM5 and LGA1700."

        } else if (productId == "monitor") {
            imgProduct.setImageResource(R.drawable.monitor)
            tvProductName.text = "Acer Nitro VGO VG240Y Gaming Monitor"
            tvPrice.text = "₱24,799"
            tvDescription.text = "The Acer Nitro VGO VG240Y is a 23.8-inch Full HD IPS gaming monitor featuring a 165Hz refresh rate and 1ms response time for ultra-smooth gameplay. AMD FreeSync Premium support eliminates screen tearing and stuttering. Its slim bezel design and ergonomic stand with tilt adjustment make it a great choice for both gaming setups and productivity workspaces."
        }

        // Back button
        btnBack.setOnClickListener {
            finish()
        }

        // Like toggle
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

        // Add to Cart
        btnAddToCart.setOnClickListener {
            Toast.makeText(this, "${tvProductName.text} added to cart!", Toast.LENGTH_SHORT).show()
        }
    }
}