package com.example.sagapp.screens.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sagapp.R
import com.example.sagapp.authentication.ui.viewmodel.LoginViewModel
import com.example.sagapp.databinding.ActivityScreensBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class ScreensActivity : AppCompatActivity() {
    private lateinit var binding:ActivityScreensBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityScreensBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragHost.id) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        binding.bottomNavigationView.setOnItemReselectedListener {
            /*No Operation*/
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.homeFragment,
                R.id.settingsFragment,
                R.id.reportsFragment,
                R.id.contactusFragment ->{

                }
            }
        }


    }
}
