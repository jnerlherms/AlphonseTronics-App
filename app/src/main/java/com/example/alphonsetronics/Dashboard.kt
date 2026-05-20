package com.example.alphonsetronics

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Dashboard : AppCompatActivity() {

    private lateinit var btnLogout: Button
    private lateinit var cardCpu: CardView
    private lateinit var cardGpu: CardView
    private lateinit var cardRam: CardView
    private lateinit var cardMonitor: CardView
    private lateinit var btnMessages: ImageButton
    private lateinit var btnSearch: ImageButton
    private lateinit var navHome: LinearLayout
    private lateinit var navCart: LinearLayout
    private lateinit var navBid: LinearLayout
    private lateinit var navLiked: LinearLayout
    private lateinit var navSettings: LinearLayout
    private lateinit var tvUsername: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnLogout = findViewById(R.id.btnLogout)
        tvUsername = findViewById(R.id.tvUsername)
        cardCpu = findViewById(R.id.cardCpu)
        cardGpu = findViewById(R.id.cardGpu)
        cardRam = findViewById(R.id.cardRam)
        cardMonitor = findViewById(R.id.cardMonitor)
        btnMessages = findViewById(R.id.btnMessages)
        btnSearch = findViewById(R.id.btnSearch)
        navHome = findViewById(R.id.navHome)
        navCart = findViewById(R.id.navCart)
        navBid = findViewById(R.id.navBid)
        navLiked = findViewById(R.id.navLiked)
        navSettings = findViewById(R.id.navSettings)

        // Display logged in user's last name, first name
        val user = UserManager.getLoggedInUser()
        if (user != null) {
            tvUsername.text = "${user.lastName}, ${user.firstName}"
        } else {
            tvUsername.text = "Guest"
        }

        cardCpu.setOnClickListener {
            val intent = Intent(this, ProductDetail::class.java)
            intent.putExtra("PRODUCT_ID", "cpu")
            startActivity(intent)
        }

        cardGpu.setOnClickListener {
            val intent = Intent(this, ProductDetail::class.java)
            intent.putExtra("PRODUCT_ID", "gpu")
            startActivity(intent)
        }

        cardRam.setOnClickListener {
            val intent = Intent(this, ProductDetail::class.java)
            intent.putExtra("PRODUCT_ID", "ram")
            startActivity(intent)
        }

        cardMonitor.setOnClickListener {
            val intent = Intent(this, ProductDetail::class.java)
            intent.putExtra("PRODUCT_ID", "monitor")
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            UserManager.logout()
            val intent = Intent(this, Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        btnSearch.setOnClickListener {
            startActivity(Intent(this, Search::class.java))
        }

        btnMessages.setOnClickListener {
            startActivity(Intent(this, Chat::class.java))
        }

        navHome.setOnClickListener {
            Toast.makeText(this, "You are on Home", Toast.LENGTH_SHORT).show()
        }

        navCart.setOnClickListener {
            startActivity(Intent(this, Cart::class.java))
        }

        navBid.setOnClickListener {
            startActivity(Intent(this, Bidding::class.java))
        }

        navLiked.setOnClickListener {
            startActivity(Intent(this, Liked::class.java))
        }

        navSettings.setOnClickListener {
            startActivity(Intent(this, Appsettings::class.java))
        }
    }
}
