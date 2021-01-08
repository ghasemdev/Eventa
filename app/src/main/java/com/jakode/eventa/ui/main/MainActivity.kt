package com.jakode.eventa.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.jakode.eventa.R
import com.jakode.eventa.databinding.ActivityMainBinding
import com.jakode.eventa.utils.enableEdgeToEdge
import com.jakode.eventa.utils.setupWithNavController

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState
    }

    private fun bottomNavLongClick() {
        featuredLongClick()
        scheduleLongClick()
        ticketsLongClick()
        accountLongClick()
    }

    private fun accountLongClick() {
        val profileView = findViewById<View>(R.id.navigation_account)
        profileView.setOnLongClickListener {
            Toast.makeText(applicationContext, "Long pressed account", Toast.LENGTH_SHORT)
                .show()
            false
        }
    }

    private fun ticketsLongClick() {
        val shopView = findViewById<View>(R.id.navigation_tickets)
        shopView.setOnLongClickListener {
            Toast.makeText(applicationContext, "Long pressed tickets", Toast.LENGTH_SHORT)
                .show()
            false
        }
    }

    private fun scheduleLongClick() {
        val searchView = findViewById<View>(R.id.navigation_schedule)
        searchView.setOnLongClickListener {
            Toast.makeText(applicationContext, "Long pressed schedule", Toast.LENGTH_SHORT)
                .show()
            false
        }
    }

    private fun featuredLongClick() {
        val homeView = findViewById<View>(R.id.navigation_featured)
        homeView.setOnLongClickListener {
            Toast.makeText(applicationContext, "Long pressed featured", Toast.LENGTH_SHORT)
                .show()
            false
        }
    }

    fun showBottomNav(isShow: Boolean) {
        binding.bottomNav.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        currentNavController = null
        _binding = null
        super.onDestroy()
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        binding.apply {
            // disable ripple highlight
            bottomNav.apply { itemRippleColor = null }
            bottomNavLongClick()

            val navGraphIds = listOf(
                R.navigation.nav_featured,
                R.navigation.nav_schedule,
                R.navigation.nav_tickets,
                R.navigation.nav_account
            )

            // Setup the bottom navigation view with a list of navigation graphs
            val controller = bottomNav.setupWithNavController(
                navGraphIds = navGraphIds,
                fragmentManager = supportFragmentManager,
                containerId = R.id.navHostContainer,
                intent = intent
            )

            currentNavController = controller
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}