package com.group.furniture_shopping_android_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.group.furniture_shopping_android_app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.activity_home_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView =
            findViewById<BottomNavigationView>(R.id.activity_main_bottom_navigation_view)
        setupWithNavController(bottomNavigationView, navController)


        // always show selected Bottom Navigation item as selected (return true)
        bottomNavigationView.setOnItemSelectedListener { item ->
            NavigationUI.onNavDestinationSelected(item, navController)
            return@setOnItemSelectedListener true
        }
    }

    fun setBottomNavigationVisibility(visibility: Int) {
        binding.activityMainBottomNavigationView.visibility = visibility
    }

}