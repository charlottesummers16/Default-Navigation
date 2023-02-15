package com.summers.defaultnavigation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.summers.defaultnavigation.R
import com.summers.defaultnavigation.base.ViewBindingActivity
import com.summers.defaultnavigation.databinding.ActivityMainBinding

class MainActivity : ViewBindingActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {

    private lateinit var navController: NavController

    override fun onInitBinding(savedInstanceState: Bundle?) {
        setUpNavigation()
        initTopActionBar()
        addDestinationChangeListener()
    }

    override fun showLoading() {
        binding.navMainBottomNavigationView.visibility = View.GONE
        binding.tbNavigationToolbar.visibility = View.GONE
        binding.loadingView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.tbNavigationToolbar.visibility = View.VISIBLE
        navController.currentDestination?.let { handleBottomNavigationViewVisibility(it) }
        binding.loadingView.visibility = View.GONE
    }

    private fun setUpNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nhFragmentNavHost) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.navMainBottomNavigationView, navController)
    }

    private fun initTopActionBar() {
        setSupportActionBar(binding.tbNavigationToolbar)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.tbNavigationToolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun addDestinationChangeListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            handleBottomNavigationViewVisibility(destination)
            when (destination.id) {
                R.id.homeFragment -> {
                    binding.tbNavigationToolbar.navigationIcon = AppCompatResources.getDrawable(this, R.drawable.baseline_home_24)
                }
                else -> {
                    binding.tbNavigationToolbar.navigationIcon = AppCompatResources.getDrawable(this, R.drawable.baseline_arrow_back_24)
                }
            }

        }
    }

    private fun handleBottomNavigationViewVisibility(navDestination: NavDestination) {
        when (navDestination.id) {
            R.id.homeFragment -> {
                binding.navMainBottomNavigationView.visibility = View.VISIBLE
            }
            else -> {
                binding.navMainBottomNavigationView.visibility = View.GONE
            }
        }
    }
}