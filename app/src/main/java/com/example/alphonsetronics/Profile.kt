package com.example.alphonsetronics

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Profile : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var tvProfileName: TextView
    private lateinit var tvProfileEmail: TextView
    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var btnSaveProfile: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        btnBack = findViewById(R.id.btnBack)
        tvProfileName = findViewById(R.id.tvProfileName)
        tvProfileEmail = findViewById(R.id.tvProfileEmail)
        etFirstName = findViewById(R.id.etFirstName)
        etLastName = findViewById(R.id.etLastName)
        etPhone = findViewById(R.id.etPhone)
        etEmail = findViewById(R.id.etEmail)
        btnSaveProfile = findViewById(R.id.btnSaveProfile)

        // Load current user data into fields
        val user = UserManager.getLoggedInUser()
        if (user != null) {
            tvProfileName.text = "${user.firstName} ${user.lastName}"
            tvProfileEmail.text = user.email
            etFirstName.setText(user.firstName)
            etLastName.setText(user.lastName)
            etPhone.setText(user.phone)
            etEmail.setText(user.email)
        }

        btnBack.setOnClickListener {
            finish()
        }

        btnSaveProfile.setOnClickListener {
            val newFirstName = etFirstName.text.toString().trim()
            val newLastName = etLastName.text.toString().trim()
            val newPhone = etPhone.text.toString().trim()

            if (newFirstName.isEmpty()) {
                etFirstName.error = "First name is required"
                return@setOnClickListener
            }

            if (newLastName.isEmpty()) {
                etLastName.error = "Last name is required"
                return@setOnClickListener
            }

            val currentUser = UserManager.getLoggedInUser()
            if (currentUser != null) {
                val updatedUser = UserManager.User(
                    firstName = newFirstName,
                    lastName = newLastName,
                    email = currentUser.email,
                    phone = newPhone,
                    password = currentUser.password
                )
                UserManager.updateUser(updatedUser)
                UserManager.setLoggedInUser(updatedUser)

                tvProfileName.text = "$newFirstName $newLastName"

                Toast.makeText(this, "Profile updated successfully!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}