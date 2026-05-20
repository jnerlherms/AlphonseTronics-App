package com.example.alphonsetronics

object CartManager {

    data class CartItem(
        val productId: String,
        val name: String,
        val price: String,
        val imageResId: Int,
        var quantity: Int = 1
    )

    private val cartItems = mutableListOf<CartItem>()

    fun add(item: CartItem) {
        val existing = cartItems.find { it.productId == item.productId }
        if (existing != null) {
            existing.quantity++
        } else {
            cartItems.add(item)
        }
    }

    fun remove(productId: String) {
        cartItems.removeAll { it.productId == productId }
    }

    fun getAll(): List<CartItem> = cartItems.toList()

    fun getTotal(): Int {
        return cartItems.sumOf { item ->
            val price = item.price
                .replace("₱", "")
                .replace(",", "")
                .trim()
                .toIntOrNull() ?: 0
            price * item.quantity
        }
    }

    fun isInCart(productId: String): Boolean = cartItems.any { it.productId == productId }

    fun clear() {
        cartItems.clear()
    }
}
