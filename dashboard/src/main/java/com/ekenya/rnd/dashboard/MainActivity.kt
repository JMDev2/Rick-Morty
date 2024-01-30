package com.ekenya.rnd.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ekenya.rnd.dashboard.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
  private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.nav_view)

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.character ->{
                    replaceFragment(CharacterFragment())
                    true
                }
                R.id.episodes ->{
                    replaceFragment(EpisodesFragment())
                    true
                }
                R.id.settings ->{
                    replaceFragment(SettingsFragment())
                    true
                }
                R.id.loctaions ->{
                    replaceFragment(LocationsFragment())
                    true
                }
                else -> false
            }
        }
        replaceFragment(CharacterFragment())

    }


    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment).commit()
    }





//        // Assuming you have a NavHostFragment with the id "fragmentContainerView" in your activity layout.
//        val navController = findNavController(R.id.fragmentContainerView)
//
//        // Initialize the AppBarConfiguration
//        appBarConfiguration = AppBarConfiguration.Builder(
//            R.id.characterFragment, R.id.LocationsFragment, R.id.EpisodesFragment, R.id.SettingsFragment
//        ).build()
//
//        // Set up the ActionBar with NavController and AppBarConfiguration
//        setupActionBarWithNavController(navController, appBarConfiguration)
//
//        // Set up the BottomNavigationView with the NavController
//        binding.navView?.let { setupWithNavController(it, navController) }
//    }
//
//    // This method is necessary if you want to handle Up navigation (back button) in your ActionBar.
//    override fun onSupportNavigateUp(): Boolean {

}
