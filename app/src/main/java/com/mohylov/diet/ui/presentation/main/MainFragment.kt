package com.mohylov.diet.ui.presentation.main

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat.getColor
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.mohylov.diet.R
import com.mohylov.diet.databinding.FragmentMainBinding
import com.mohylov.diet.ui.presentation.base.viewBinding
import com.mohylov.diet.ui.presentation.utils.applyInsets
import com.mohylov.diet.ui.presentation.utils.setVisibility

class MainFragment : Fragment(R.layout.fragment_main), BottomNavigator {

    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.applyInsets(top = true, bottom = true)
        val navHost = childFragmentManager.findFragmentById(R.id.diet_nav_host) as NavHostFragment
        binding.bottomNavMenu.setupWithNavController(navHost.navController)
    }

    override fun showNavigator() {
        binding.bottomNavMenu.setVisibility(true)
    }

    override fun hideNavigator() {
        binding.bottomNavMenu.setVisibility(false)
    }
}