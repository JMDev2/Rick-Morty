package com.ekenya.rnd.dashboard

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ekenya.rnd.dashboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.characterFragment,
                R.id.locationsFragment,
                R.id.episodesFragment,
                R.id.settingsFragment
            )
        )

// Check if ActionBar is not null before calling setupActionBarWithNavController
        if (supportActionBar != null) {
            setupActionBarWithNavController(navController, appBarConfiguration)
        }

        navView.setupWithNavController(navController)

    }
}
