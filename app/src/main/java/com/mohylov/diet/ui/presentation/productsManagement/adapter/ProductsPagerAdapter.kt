package com.mohylov.diet.ui.presentation.productsManagement.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mohylov.diet.ui.presentation.productsManagement.default.DefaultProductFragment
import com.mohylov.diet.ui.presentation.productsManagement.personal.PersonalProductsFragment

class ProductsPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(
        fragmentManager,
        lifecycle
    ) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PersonalProductsFragment()
            1 -> DefaultProductFragment()
            else -> Fragment()
        }
    }
}