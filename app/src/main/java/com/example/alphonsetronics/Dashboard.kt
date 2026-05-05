package com.example.alphonsetronics

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageButton

class Dashboard : AppCompatActivity() {

    private lateinit var cardCpu: CardView
    private lateinit var cardGpu: CardView
    private lateinit var cardRam: CardView
    private lateinit var cardMonitor: CardView

    private lateinit var btnMessages: ImageButton
    private lateinit var btnLiked: ImageButton
    private lateinit var btnCart: ImageButton

    private lateinit var navHome: LinearLayout
    private lateinit var navSearch: LinearLayout
    private lateinit var navCart: LinearLayout
    private lateinit var navLiked: LinearLayout
    private lateinit var navSettings: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cardCpu = findViewById(R.id.cardCpu)
        cardGpu = findViewById(R.id.cardGpu)
        cardRam = findViewById(R.id.cardRam)
        cardMonitor = findViewById(R.id.cardMonitor)

        btnMessages = findViewById(R.id.btnMessages)
        btnLiked = findViewById(R.id.btnLiked)
        btnCart = findViewById(R.id.btnCart)

        navHome = findViewById(R.id.navHome)
        navSearch = findViewById(R.id.navSearch)
        navCart = findViewById(R.id.navCart)
        navLiked = findViewById(R.id.navLiked)
        navSettings = findViewById(R.id.navSettings)


        // TODO: Replace the Toasts once naa na ang screens nila
        cardCpu.setOnClickListener {
            Toast.makeText(this, "Intel Core i9-13900K selected", Toast.LENGTH_SHORT).show()
        }

        cardGpu.setOnClickListener {
            Toast.makeText(this, "NVIDIA RTX 4080 Super selected", Toast.LENGTH_SHORT).show()
        }

        cardRam.setOnClickListener {
            Toast.makeText(this, "Corsair Vengeance 32GB DDR5 selected", Toast.LENGTH_SHORT).show()
        }

        cardMonitor.setOnClickListener {
            Toast.makeText(this, "LG UltraGear 27\" 4K 144Hz selected", Toast.LENGTH_SHORT).show()
        }

        // Top bar button clicks
        btnMessages.setOnClickListener {
            Toast.makeText(this, "My Messages", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to Messages screen
        }

        btnLiked.setOnClickListener {
            Toast.makeText(this, "Liked Products", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to Liked Products screen
        }

        btnCart.setOnClickListener {
            Toast.makeText(this, "My Cart", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to Cart screen
        }

        // Bottom nav clicks
        navHome.setOnClickListener {
            Toast.makeText(this, "You are on Home", Toast.LENGTH_SHORT).show()
        }

        navSearch.setOnClickListener {
            Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to Search screen
        }

        navCart.setOnClickListener {
            Toast.makeText(this, "My Cart", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to Cart screen
        }

        navLiked.setOnClickListener {
            Toast.makeText(this, "Liked Products", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to Liked Products screen
        }

        navSettings.setOnClickListener {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to Settings screen
        }
    }
}