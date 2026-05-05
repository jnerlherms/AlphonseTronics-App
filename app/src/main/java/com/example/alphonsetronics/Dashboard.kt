package com.example.alphonsetronics

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

        navHome = findViewById(R.id.navHome)
        navSearch = findViewById(R.id.navSearch)
        navCart = findViewById(R.id.navCart)
        navLiked = findViewById(R.id.navLiked)
        navSettings = findViewById(R.id.navSettings)


        cardCpu.setOnClickListener {
            Toast.makeText(this, "AMD Ryzen 5 7600 selected", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to Product Detail Screen 1
        }

        cardGpu.setOnClickListener {
            Toast.makeText(this, "Colorful GeForce RTX 5060 selected", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to Product Detail Screen 2
        }

        cardRam.setOnClickListener {
            Toast.makeText(this, "Corsair Vengeance 32GB DDR5 selected", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to Product Detail Screen 3
        }

        cardMonitor.setOnClickListener {
            Toast.makeText(this, "Acer Nitro VG240Y selected", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to Product Detail Screen 4
        }

        // Top bar buttons
        btnMessages.setOnClickListener {
            Toast.makeText(this, "My Messages", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to Messages Screen
        }

        navHome.setOnClickListener {
            Toast.makeText(this, "You are on Home", Toast.LENGTH_SHORT).show()
        }

        navSearch.setOnClickListener {
            Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to Search Screen
        }

        navCart.setOnClickListener {
            Toast.makeText(this, "My Cart", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to My Cart Screen
        }

        navLiked.setOnClickListener {
            Toast.makeText(this, "Liked Products", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to Liked Products Screen
        }

        navSettings.setOnClickListener {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to Settings Screen
        }
    }
}