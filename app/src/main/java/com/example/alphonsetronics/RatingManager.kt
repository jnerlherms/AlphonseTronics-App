package com.example.alphonsetronics

object RatingManager {
    private val ratings = mutableMapOf<String, Int>()

    fun setRating(productId: String, rating: Int) {
        ratings[productId] = rating
    }

    fun getRating(productId: String): Int {
        return ratings[productId] ?: 0
    }

    fun hasRated(productId: String): Boolean {
        return ratings.containsKey(productId)
    }
}
