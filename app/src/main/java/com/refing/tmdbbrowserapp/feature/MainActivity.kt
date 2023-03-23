package com.refing.tmdbbrowserapp.feature


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.refing.tmdbbrowserapp.R
import com.refing.tmdbbrowserapp.databinding.ActivityMainBinding
import com.refing.tmdbbrowserapp.feature.main.HomeFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import com.refing.tmdbbrowserapp.feature.favorite.FavoriteFragment


class MainActivity : AppCompatActivity(){


    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_favorite
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.nav_host_fragment, HomeFragment())
//                .commit()
//            supportActionBar?.title = getString(R.string.app_name)
//        }


    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        var fragment: Fragment? = null
//        var title = getString(R.string.app_name)
//        when (item.itemId) {
//            R.id.navigation_home -> {
//                fragment = HomeFragment()
//                title = getString(R.string.app_name)
//            }
//            R.id.navigation_favorite -> {
//                fragment = FavoriteFragment()
//                title = getString(R.string.favorite)
//            }
////            R.id.nav_map -> {
////                Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show()
////            }
//        }
//        if (fragment != null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.nav_host_fragment, fragment)
//                .commit()
//        }
//        supportActionBar?.title = title
//
//        return true
//    }




}