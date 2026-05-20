package com.example.alphonsetronics

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Search : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var tilSearch: TextInputLayout
    private lateinit var etSearch: TextInputEditText
    private lateinit var rvSearchResults: RecyclerView
    private lateinit var layoutEmpty: LinearLayout
    private lateinit var layoutNoResults: LinearLayout

    private lateinit var navHome: LinearLayout
    private lateinit var navCart: LinearLayout
    private lateinit var navBid: LinearLayout
    private lateinit var navLiked: LinearLayout
    private lateinit var navSettings: LinearLayout

    private lateinit var adapter: SearchResultsAdapter

    // Full product list
    private val allProducts = listOf(
        SearchResultsAdapter.Product(
            productId = "cpu",
            name = "AMD Ryzen 5 7600 Desktop Processor",
            price = "₱32,999",
            imageResId = R.drawable.cpu
        ),
        SearchResultsAdapter.Product(
            productId = "gpu",
            name = "Colorful GeForce RTX 5060 Battle AX DUO 8GB GDDR7",
            price = "₱89,999",
            imageResId = R.drawable.gpu
        ),
        SearchResultsAdapter.Product(
            productId = "ram",
            name = "Corsair Vengeance 32GB DDR5 RAM",
            price = "₱9,499",
            imageResId = R.drawable.ram
        ),
        SearchResultsAdapter.Product(
            productId = "monitor",
            name = "Acer Nitro VGO VG240Y Gaming Monitor",
            price = "₱24,799",
            imageResId = R.drawable.monitor
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        btnBack = findViewById(R.id.btnBack)
        tilSearch = findViewById(R.id.tilSearch)
        etSearch = findViewById(R.id.etSearch)
        rvSearchResults = findViewById(R.id.rvSearchResults)
        layoutEmpty = findViewById(R.id.layoutEmpty)
        layoutNoResults = findViewById(R.id.layoutNoResults)

        navHome = findViewById(R.id.navHome)
        navCart = findViewById(R.id.navCart)
        navBid = findViewById(R.id.navBid)
        navLiked = findViewById(R.id.navLiked)
        navSettings = findViewById(R.id.navSettings)

        // Set up adapter with empty list initially
        adapter = SearchResultsAdapter(mutableListOf()) { product ->
            val intent = Intent(this, ProductDetail::class.java)
            intent.putExtra("PRODUCT_ID", product.productId)
            startActivity(intent)
        }
        rvSearchResults.layoutManager = LinearLayoutManager(this)
        rvSearchResults.adapter = adapter

        // empty search
        showEmptyState()

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim()
                if (query.isEmpty()) {
                    showEmptyState()
                } else {
                    performSearch(query)
                }
            }
        })

        btnBack.setOnClickListener {
            finish()
        }

        navHome.setOnClickListener {
            startActivity(Intent(this, Dashboard::class.java))
            finish()
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

    private fun performSearch(query: String) {
        val results = allProducts.filter { product ->
            product.name.contains(query, ignoreCase = true)
        }.toMutableList()

        if (results.isEmpty()) {
            rvSearchResults.visibility = View.GONE
            layoutEmpty.visibility = View.GONE
            layoutNoResults.visibility = View.VISIBLE
        } else {
            rvSearchResults.visibility = View.VISIBLE
            layoutEmpty.visibility = View.GONE
            layoutNoResults.visibility = View.GONE
            adapter.updateResults(results)
        }
    }

    private fun showEmptyState() {
        rvSearchResults.visibility = View.GONE
        layoutEmpty.visibility = View.VISIBLE
        layoutNoResults.visibility = View.GONE
    }
}
