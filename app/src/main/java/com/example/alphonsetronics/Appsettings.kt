package com.example.alphonsetronics

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.*

class Appsettings : AppCompatActivity() {

    private lateinit var navHome: LinearLayout
    private lateinit var navCart: LinearLayout
    private lateinit var navBid: LinearLayout
    private lateinit var navLiked: LinearLayout
    private lateinit var navSettings: LinearLayout
    private lateinit var switchNotifications: Switch
  //  private lateinit var switchDarkMode: Switch                   // removed
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        navHome = findViewById(R.id.navHome)
        navCart = findViewById(R.id.navCart)
        navBid = findViewById(R.id.navBid)
        navLiked = findViewById(R.id.navLiked)
        navSettings = findViewById(R.id.navSettings)
        switchNotifications = findViewById(R.id.switchNotifications)
       // switchDarkMode = findViewById(R.id.switchDarkMode)        // removed
        btnLogout = findViewById(R.id.btnLogout)

        val rowProfile = findViewById<androidx.cardview.widget.CardView>(R.id.rowProfile)
        rowProfile.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        val rowChangePassword = findViewById<androidx.cardview.widget.CardView>(R.id.rowChangePassword)
        rowChangePassword.setOnClickListener {
            val intent = Intent(this, ChangePassword::class.java)
            startActivity(intent)
        }

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

        navBid.setOnClickListener {
            startActivity(Intent(this, Bidding::class.java))
        }

        navLiked.setOnClickListener {
            Toast.makeText(this, "Liked Products", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Liked::class.java)
            startActivity(intent)
        }

        navSettings.setOnClickListener {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
        }

        switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Notifications enabled", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifications disabled", Toast.LENGTH_SHORT).show()
            }
        }
//
//        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                Toast.makeText(this, "Dark Mode enabled", Toast.LENGTH_SHORT).show()          // removed
//            } else {
//                Toast.makeText(this, "Dark Mode disabled", Toast.LENGTH_SHORT).show()
//            }
//        }

        btnLogout.setOnClickListener {
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}