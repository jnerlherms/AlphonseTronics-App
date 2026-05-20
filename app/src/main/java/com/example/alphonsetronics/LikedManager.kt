package com.example.alphonsetronics

object LikedManager {

    data class LikedItem(
        val productId: String,
        val name: String,
        val price: String,
        val imageResId: Int
    )

    private val likedItems = mutableListOf<LikedItem>()

    fun add(item: LikedItem) {
        if (likedItems.none { it.productId == item.productId }) {
            likedItems.add(item)
        }
    }

    fun remove(productId: String) {
        likedItems.removeAll { it.productId == productId }
    }

    fun isLiked(productId: String): Boolean = likedItems.any { it.productId == productId }

    fun getAll(): List<LikedItem> = likedItems.toList()
}
