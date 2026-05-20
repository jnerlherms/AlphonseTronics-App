package com.example.alphonsetronics

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.NumberFormat
import java.util.Locale

class Bidding : AppCompatActivity() {

    private lateinit var tvCountdown: TextView
    private lateinit var tvCurrentBid: TextView
    private lateinit var tvHighestBidder: TextView
    private lateinit var tilBidAmount: TextInputLayout
    private lateinit var etBidAmount: TextInputEditText
    private lateinit var btnPlaceBid: Button
    private lateinit var bidHistoryContainer: LinearLayout
    private lateinit var tvNoBids: TextView

    private lateinit var navHome: LinearLayout
    private lateinit var navCart: LinearLayout
    private lateinit var navBid: LinearLayout
    private lateinit var navLiked: LinearLayout
    private lateinit var navSettings: LinearLayout

    private var currentBidAmount = 20000
    private val startingPrice = 20000
    private val bidHistory = mutableListOf<String>()
    private var countDownTimer: CountDownTimer? = null

    // 24 hours in milliseconds
    private val auctionDuration = 24 * 60 * 60 * 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bidding)

        tvCountdown = findViewById(R.id.tvCountdown)
        tvCurrentBid = findViewById(R.id.tvCurrentBid)
        tvHighestBidder = findViewById(R.id.tvHighestBidder)
        tilBidAmount = findViewById(R.id.tilBidAmount)
        etBidAmount = findViewById(R.id.etBidAmount)
        btnPlaceBid = findViewById(R.id.btnPlaceBid)
        bidHistoryContainer = findViewById(R.id.bidHistoryContainer)
        tvNoBids = findViewById(R.id.tvNoBids)

        navHome = findViewById(R.id.navHome)
        navCart = findViewById(R.id.navCart)
        navBid = findViewById(R.id.navBid)
        navLiked = findViewById(R.id.navLiked)
        navSettings = findViewById(R.id.navSettings)

        startCountdown()

        btnPlaceBid.setOnClickListener {
            placeBid()
        }

        navHome.setOnClickListener {
            startActivity(Intent(this, Dashboard::class.java))
        }

        navCart.setOnClickListener {
            startActivity(Intent(this, Cart::class.java))
        }

        navBid.setOnClickListener {
            Toast.makeText(this, "You are on Auction", Toast.LENGTH_SHORT).show()
        }

        navLiked.setOnClickListener {
            startActivity(Intent(this, Liked::class.java))
        }

        navSettings.setOnClickListener {
            startActivity(Intent(this, Appsettings::class.java))
        }
    }

    private fun startCountdown() {
        countDownTimer = object : CountDownTimer(auctionDuration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val hours = millisUntilFinished / 3600000
                val minutes = (millisUntilFinished % 3600000) / 60000
                val seconds = (millisUntilFinished % 60000) / 1000
                tvCountdown.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
            }

            override fun onFinish() {
                tvCountdown.text = "00:00:00"
                tvCountdown.setTextColor(Color.GRAY)
                btnPlaceBid.isEnabled = false
                btnPlaceBid.text = "AUCTION ENDED"
                Toast.makeText(this@Bidding, "Auction has ended!", Toast.LENGTH_LONG).show()
            }
        }.start()
    }

    private fun placeBid() {
        val bidText = etBidAmount.text.toString().trim()

        if (bidText.isEmpty()) {
            tilBidAmount.error = "Please enter a bid amount"
            return
        }

        val bidAmount = bidText.toIntOrNull()
        if (bidAmount == null) {
            tilBidAmount.error = "Please enter a valid amount"
            return
        }

        if (bidAmount <= currentBidAmount) {
            tilBidAmount.error = "Your bid must be higher than ₱${formatPrice(currentBidAmount)}"
            return
        }

        tilBidAmount.error = null

        // Get logged in user name
        val user = UserManager.getLoggedInUser()
        val bidderName = if (user != null) "${user.lastName}, ${user.firstName}" else "Anonymous"

        currentBidAmount = bidAmount

        // Update UI
        tvCurrentBid.text = "₱${formatPrice(currentBidAmount)}"
        tvHighestBidder.text = "Highest bidder: $bidderName"

        // Add to history
        val historyEntry = "$bidderName — ₱${formatPrice(bidAmount)}"
        bidHistory.add(0, historyEntry)

        // Update history view
        updateBidHistory()

        etBidAmount.text?.clear()

        Toast.makeText(this, "Bid of ₱${formatPrice(bidAmount)} placed successfully!", Toast.LENGTH_SHORT).show()
    }

    private fun updateBidHistory() {
        bidHistoryContainer.removeAllViews()
        tvNoBids.visibility = android.view.View.GONE

        if (bidHistory.isEmpty()) {
            bidHistoryContainer.addView(tvNoBids)
            tvNoBids.visibility = android.view.View.VISIBLE
            return
        }

        bidHistory.forEachIndexed { index, entry ->
            val row = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).also { it.bottomMargin = 8 }
                setPadding(0, 8, 0, 8)
            }

            val rankText = if (index == 0) "🥇" else if (index == 1) "🥈" else if (index == 2) "🥉" else "  ${index + 1}."
            val tvRank = TextView(this).apply {
                text = rankText
                textSize = 14f
                layoutParams = LinearLayout.LayoutParams(40, LinearLayout.LayoutParams.WRAP_CONTENT)
                gravity = Gravity.CENTER_VERTICAL
            }

            val tvEntry = TextView(this).apply {
                text = entry
                textSize = 13f
                setTextColor(Color.parseColor("#333333"))
                layoutParams = LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f
                )
                gravity = Gravity.CENTER_VERTICAL
            }

            row.addView(tvRank)
            row.addView(tvEntry)
            bidHistoryContainer.addView(row)

            // Add divider except last
            if (index < bidHistory.size - 1) {
                val divider = android.view.View(this).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, 1
                    ).also { it.bottomMargin = 4 }
                    setBackgroundColor(Color.parseColor("#EEEEEE"))
                }
                bidHistoryContainer.addView(divider)
            }
        }
    }

    private fun formatPrice(amount: Int): String {
        return NumberFormat.getNumberInstance(Locale.US).format(amount)
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}
