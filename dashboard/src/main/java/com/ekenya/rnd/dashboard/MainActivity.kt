package com.ekenya.rnd.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.ekenya.rnd.dashboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Assuming you have a NavHostFragment with the id "nav_host_fragment" in your activity layout.
        val navController = Navigation.findNavController(this, R.id.fragmentContainerView)

        // Initialize the AppBarConfiguration
        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_character, R.id.navigation_locations, R.id.navigation_episodes, R.id.navigation_settings
        ).build()

        // Set up the ActionBar with NavController and AppBarConfiguration
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        // Set up the BottomNavigationView with the NavController
        binding.navView?.let { NavigationUI.setupWithNavController(it, navController) }
    }

    // This method is necessary if you want to handle Up navigation (back button) in your ActionBar.
    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.fragmentContainerView)
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }
}