package com.example.alphonsetronics

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Chat : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var rvMessages: RecyclerView
    private lateinit var etMessage: EditText
    private lateinit var btnSend: ImageButton

    private val messages = mutableListOf<ChatMessage>()
    private lateinit var adapter: ChatAdapter

    // Auto reply messages
    private val autoReplies = listOf(
        "Hello! How can I help you today?",
        "Thank you for reaching out! Let me check that for you.",
        "Sure! Our products come with a 1-year warranty.",
        "You can visit our store at Cebu IT Park for in-person assistance.",
        "Is there anything else I can help you with?",
        "Our team will get back to you within 24 hours.",
        "Great choice! That product is one of our best sellers.",
        "We offer free delivery for orders above ₱5,000."
    )
    private var autoReplyIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        btnBack = findViewById(R.id.btnBack)
        rvMessages = findViewById(R.id.rvMessages)
        etMessage = findViewById(R.id.etMessage)
        btnSend = findViewById(R.id.btnSend)

        // Setup RecyclerView
        adapter = ChatAdapter(messages)
        rvMessages.layoutManager = LinearLayoutManager(this).apply {
            stackFromEnd = true
        }
        rvMessages.adapter = adapter

        // Welcome message from support
        addSupportMessage("Hi ${UserManager.getLoggedInUser()?.firstName ?: "there"}! 👋 Welcome to AlphonseTronics Support. How can I help you today?")

        btnBack.setOnClickListener {
            finish()
        }

        btnSend.setOnClickListener {
            val text = etMessage.text.toString().trim()
            if (text.isEmpty()) {
                Toast.makeText(this, "Please type a message", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Add user message
            addUserMessage(text)
            etMessage.setText("")

            // Auto-reply after a short delay
            rvMessages.postDelayed({
                addSupportMessage(autoReplies[autoReplyIndex % autoReplies.size])
                autoReplyIndex++
            }, 1000)
        }
    }

    private fun addUserMessage(text: String) {
        messages.add(ChatMessage(text, isSentByUser = true, time = getCurrentTime()))
        adapter.notifyItemInserted(messages.size - 1)
        rvMessages.scrollToPosition(messages.size - 1)
    }

    private fun addSupportMessage(text: String) {
        messages.add(ChatMessage(text, isSentByUser = false, time = getCurrentTime()))
        adapter.notifyItemInserted(messages.size - 1)
        rvMessages.scrollToPosition(messages.size - 1)
    }

    private fun getCurrentTime(): String {
        return SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
    }
}