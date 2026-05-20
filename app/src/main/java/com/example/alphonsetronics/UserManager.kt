package com.example.alphonsetronics

object UserManager {

    data class User(
        val firstName: String,
        val lastName: String,
        val email: String,
        val phone: String,
        val password: String
    )

    private val users = mutableListOf<User>()
    private var loggedInUser: User? = null

    fun register(user: User): Boolean {
        if (users.any { it.email.equals(user.email, ignoreCase = true) }) {
            return false
        }
        users.add(user)
        return true
    }

    fun login(email: String, password: String): User? {
        return users.find {
            it.email.equals(email.trim(), ignoreCase = true) &&
            it.password == password.trim()
        }
    }

    fun getUserByEmail(email: String): User? {
        return users.find { it.email.equals(email.trim(), ignoreCase = true) }
    }

    fun setLoggedInUser(user: User) {
        loggedInUser = user
    }

    fun getLoggedInUser(): User? = loggedInUser

    fun logout() {
        loggedInUser = null
    }

    fun updateUser(updatedUser: UserManager.User) {

    }
}
