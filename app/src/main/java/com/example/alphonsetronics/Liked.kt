package com.example.alphonsetronics

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Liked : AppCompatActivity() {

    private lateinit var navHome: LinearLayout
    private lateinit var navCart: LinearLayout
    private lateinit var navLiked: LinearLayout
    private lateinit var navSettings: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liked)

        navHome = findViewById(R.id.navHome)
        navCart = findViewById(R.id.navCart)
        navLiked = findViewById(R.id.navLiked)
        navSettings = findViewById(R.id.navSettings)

        navHome.setOnClickListener {
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        navCart.setOnClickListener {
            Toast.makeText(this, "My Cart", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Cart::class.java)
            startActivity(intent)
        }

        navLiked.setOnClickListener {
            Toast.makeText(this, "Liked Products", Toast.LENGTH_SHORT).show()
        }

        navSettings.setOnClickListener {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Appsettings::class.java)
            startActivity(intent)
        }
    }
}