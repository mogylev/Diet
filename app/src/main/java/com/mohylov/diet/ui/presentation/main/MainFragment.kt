package com.mohylov.diet.ui.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.mohylov.diet.R
import com.mohylov.diet.databinding.FragmentMainBinding
import com.mohylov.diet.ui.presentation.base.viewBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHost = childFragmentManager.findFragmentById(R.id.diet_nav_host) as NavHostFragment
        binding.bottomNavMenu.setupWithNavController(navHost.navController)
    }
}