package com.group.furniture_shopping_android_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.activity_home_nav_host_fragment) as NavHostFragment?
        val navController = navHostFragment!!.navController
        val bottomNavigationView =
            findViewById<BottomNavigationView>(R.id.activity_home_bottom_navigation_view)
        setupWithNavController(bottomNavigationView, navController)
    }
}