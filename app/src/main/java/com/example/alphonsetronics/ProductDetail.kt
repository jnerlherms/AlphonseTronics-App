package com.example.alphonsetronics

import android.content.Intent
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
    private lateinit var tvRatingStatus: TextView

    private lateinit var star1: ImageButton
    private lateinit var star2: ImageButton
    private lateinit var star3: ImageButton
    private lateinit var star4: ImageButton
    private lateinit var star5: ImageButton

    private var isLiked = false
    private var currentRating = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product_detail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnBack = findViewById(R.id.btnBack)
        btnLiked = findViewById(R.id.btnLiked)
        btnAddToCart = findViewById(R.id.btnAddToCart)
        imgProduct = findViewById(R.id.imgProduct)
        tvProductName = findViewById(R.id.tvProductName)
        tvPrice = findViewById(R.id.tvPrice)
        tvDescription = findViewById(R.id.tvDescription)
        tvRatingStatus = findViewById(R.id.tvRatingStatus)

        star1 = findViewById(R.id.star1)
        star2 = findViewById(R.id.star2)
        star3 = findViewById(R.id.star3)
        star4 = findViewById(R.id.star4)
        star5 = findViewById(R.id.star5)

        val productId = intent.getStringExtra("PRODUCT_ID") ?: "cpu"
        var productImageResId = R.drawable.cpu

        if (productId == "cpu") {
            productImageResId = R.drawable.cpu
            imgProduct.setImageResource(productImageResId)
            tvProductName.text = "AMD Ryzen 5 7600 Desktop Processor"
            tvPrice.text = "₱32,999"
            tvDescription.text = "The AMD Ryzen 5 7600 is a 6-core, 12-thread desktop processor built on the Zen 4 architecture with Socket AM5 compatibility. It features a base clock of 3.8GHz and a boost clock of up to 5.1GHz, making it an excellent choice for gaming and everyday computing tasks. Supports DDR5 memory and PCIe 5.0 for a future-ready platform."
        } else if (productId == "gpu") {
            productImageResId = R.drawable.gpu
            imgProduct.setImageResource(productImageResId)
            tvProductName.text = "Colorful GeForce RTX 5060 Battle AX DUO 8GB GDDR7"
            tvPrice.text = "₱89,999"
            tvDescription.text = "The Colorful GeForce RTX 5060 Battle AX DUO features 8GB of next-gen GDDR7 memory and is powered by NVIDIA's latest Blackwell architecture. It delivers exceptional 1080p and 1440p gaming performance with support for DLSS 4, ray tracing, and AI-powered frame generation. Dual-fan cooling keeps thermals low even under heavy load."
        } else if (productId == "ram") {
            productImageResId = R.drawable.ram
            imgProduct.setImageResource(productImageResId)
            tvProductName.text = "Corsair Vengeance 32GB DDR5 RAM"
            tvPrice.text = "₱9,499"
            tvDescription.text = "The Corsair Vengeance DDR5 32GB (2x16GB) kit operates at 5600MHz with low-latency timings, delivering blazing-fast performance for gaming, content creation, and multitasking. Built with a sleek black aluminum heat spreader for improved thermal performance. Compatible with Intel and AMD DDR5 platforms including AM5 and LGA1700."
        } else if (productId == "monitor") {
            productImageResId = R.drawable.monitor
            imgProduct.setImageResource(productImageResId)
            tvProductName.text = "Acer Nitro VGO VG240Y Gaming Monitor"
            tvPrice.text = "₱24,799"
            tvDescription.text = "The Acer Nitro VGO VG240Y is a 23.8-inch Full HD IPS gaming monitor featuring a 165Hz refresh rate and 1ms response time for ultra-smooth gameplay. AMD FreeSync Premium support eliminates screen tearing and stuttering. Its slim bezel design and ergonomic stand with tilt adjustment make it a great choice for both gaming setups and productivity workspaces."
        }

        // Load saved rating
        currentRating = RatingManager.getRating(productId)
        updateStars(currentRating)
        if (RatingManager.hasRated(productId)) {
            tvRatingStatus.text = "Your rating: $currentRating / 5"
        }

        // Load liked state
        isLiked = LikedManager.isLiked(productId)
        btnLiked.setImageResource(
            if (isLiked) android.R.drawable.btn_star_big_on
            else android.R.drawable.btn_star_big_off
        )

        btnBack.setOnClickListener { finish() }

        // Star rating clicks
        val stars = listOf(star1, star2, star3, star4, star5)
        stars.forEachIndexed { index, star ->
            star.setOnClickListener {
                val rating = index + 1
                currentRating = rating
                RatingManager.setRating(productId, rating)
                updateStars(rating)
                tvRatingStatus.text = "Your rating: $rating / 5 ★ — Thank you!"
                Toast.makeText(this, "You rated this $rating out of 5 stars!", Toast.LENGTH_SHORT).show()
            }
        }

        val imageResId = productImageResId

        btnLiked.setOnClickListener {
            isLiked = !isLiked
            if (isLiked) {
                btnLiked.setImageResource(android.R.drawable.btn_star_big_on)
                LikedManager.add(
                    LikedManager.LikedItem(
                        productId = productId,
                        name = tvProductName.text.toString(),
                        price = tvPrice.text.toString(),
                        imageResId = imageResId
                    )
                )
                Toast.makeText(this, "Added to Liked Products!", Toast.LENGTH_SHORT).show()
            } else {
                btnLiked.setImageResource(android.R.drawable.btn_star_big_off)
                LikedManager.remove(productId)
                Toast.makeText(this, "Removed from Liked Products.", Toast.LENGTH_SHORT).show()
            }
        }

        btnAddToCart.setOnClickListener {
            CartManager.add(
                CartManager.CartItem(
                    productId = productId,
                    name = tvProductName.text.toString(),
                    price = tvPrice.text.toString(),
                    imageResId = imageResId
                )
            )
            Toast.makeText(this, "${tvProductName.text} added to cart!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateStars(rating: Int) {
        val stars = listOf(star1, star2, star3, star4, star5)
        stars.forEachIndexed { index, star ->
            if (index < rating) {
                star.setImageResource(android.R.drawable.btn_star_big_on)
            } else {
                star.setImageResource(android.R.drawable.btn_star_big_off)
            }
        }
    }
}
