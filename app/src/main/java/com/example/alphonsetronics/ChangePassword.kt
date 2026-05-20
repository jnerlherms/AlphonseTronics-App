package com.example.alphonsetronics

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChangePassword : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var etCurrentPassword: EditText
    private lateinit var etNewPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnChangePassword: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        btnBack = findViewById(R.id.btnBack)
        etCurrentPassword = findViewById(R.id.etCurrentPassword)
        etNewPassword = findViewById(R.id.etNewPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnChangePassword = findViewById(R.id.btnChangePassword)

        btnBack.setOnClickListener {
            finish()
        }

        btnChangePassword.setOnClickListener {
            val currentPassword = etCurrentPassword.text.toString().trim()
            val newPassword = etNewPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            // Validate inputs
            if (currentPassword.isEmpty()) {
                etCurrentPassword.error = "Please enter your current password"
                return@setOnClickListener
            }

            if (newPassword.isEmpty()) {
                etNewPassword.error = "Please enter a new password"
                return@setOnClickListener
            }

            if (newPassword.length < 6) {
                etNewPassword.error = "Password must be at least 6 characters"
                return@setOnClickListener
            }

            if (confirmPassword.isEmpty()) {
                etConfirmPassword.error = "Please confirm your new password"
                return@setOnClickListener
            }

            if (newPassword != confirmPassword) {
                etConfirmPassword.error = "Passwords do not match"
                return@setOnClickListener
            }

            // Check current password is correct
            val currentUser = UserManager.getLoggedInUser()
            if (currentUser == null) {
                Toast.makeText(this, "No user logged in", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (currentUser.password != currentPassword) {
                etCurrentPassword.error = "Current password is incorrect"
                return@setOnClickListener
            }

            if (newPassword == currentPassword) {
                etNewPassword.error = "New password must be different from current password"
                return@setOnClickListener
            }

            // Update password
            val updatedUser = UserManager.User(
                firstName = currentUser.firstName,
                lastName = currentUser.lastName,
                email = currentUser.email,
                phone = currentUser.phone,
                password = newPassword
            )
            UserManager.updateUser(updatedUser)
            UserManager.setLoggedInUser(updatedUser)

            Toast.makeText(this, "Password updated successfully!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}