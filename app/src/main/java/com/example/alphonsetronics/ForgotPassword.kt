package com.example.alphonsetronics

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ForgotPassword : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var etEmail: EditText
    private lateinit var etNewPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnResetPassword: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        btnBack = findViewById(R.id.btnBack)
        etEmail = findViewById(R.id.etEmail)
        etNewPassword = findViewById(R.id.etNewPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnResetPassword = findViewById(R.id.btnResetPassword)

        btnBack.setOnClickListener {
            finish()
        }

        btnResetPassword.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val newPassword = etNewPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            // Validate email
            if (email.isEmpty()) {
                etEmail.error = "Please enter your email"
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Enter a valid email address"
                return@setOnClickListener
            }

            // Validate new password
            if (newPassword.isEmpty()) {
                etNewPassword.error = "Please enter a new password"
                return@setOnClickListener
            }

            if (newPassword.length < 6) {
                etNewPassword.error = "Password must be at least 6 characters"
                return@setOnClickListener
            }

            // Validate confirm password
            if (confirmPassword.isEmpty()) {
                etConfirmPassword.error = "Please confirm your new password"
                return@setOnClickListener
            }

            if (newPassword != confirmPassword) {
                etConfirmPassword.error = "Passwords do not match"
                return@setOnClickListener
            }

            // Check if email exists in UserManager
            val user = UserManager.getUserByEmail(email)
            if (user == null) {
                etEmail.error = "No account found with this email"
                return@setOnClickListener
            }

            // Update the password
            val updatedUser = UserManager.User(
                firstName = user.firstName,
                lastName = user.lastName,
                email = user.email,
                phone = user.phone,
                password = newPassword
            )
            UserManager.updateUser(updatedUser)

            Toast.makeText(this, "Password reset successfully! Please log in.", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}