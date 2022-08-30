package com.heyproject.storyapp.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.heyproject.storyapp.R
import com.heyproject.storyapp.databinding.ActivityHomeBinding
import com.heyproject.storyapp.model.User
import com.heyproject.storyapp.util.UserPreference

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController
    private lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_home) as NavHostFragment
        navController = navHostFragment.navController

        userPreference = UserPreference(this)

        isLoggedIn()
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navController.navigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                userPreference.removeSharedPref()
                navController.navigate(R.id.action_homeFragment_to_mainActivity)
                true
            }
            else -> {
                true
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)
        return true
    }

    private fun isLoggedIn() {
        val user: User = userPreference.getUser()
        if (user.token.isNullOrEmpty()) {
            navController.navigate(R.id.action_homeFragment_to_mainActivity)
        }
    }
}