package com.fmaldonado.akiyama.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.fmaldonado.akiyama.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_host) as NavHostFragment
        navController = navHostFragment.navController

        bottom_nav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { navController: NavController, navDestination: NavDestination, bundle: Bundle? ->
            Log.d("Destination", navDestination.id.toString())
            Log.d("Destination", R.id.homeFragment.toString())

            when (navDestination.id) {
                R.id.homeFragment, R.id.aboutFragment -> showToolbar()
                R.id.searchFragment -> showSearchBar()
            }
        }

    }

    private fun showToolbar() {
        toolbar.visibility = View.VISIBLE
        searchBar.visibility = View.INVISIBLE
    }

    private fun showSearchBar() {
        toolbar.visibility = View.INVISIBLE
        searchBar.visibility = View.VISIBLE
    }
}