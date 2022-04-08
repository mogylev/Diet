package com.mohylov.diet.ui.presentation.productsManagement.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mohylov.diet.ui.presentation.productsManagement.defaultProducts.DefaultProductsFragment
import com.mohylov.diet.ui.presentation.productsManagement.personalProducts.PersonalProductsFragment

class ProductsPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(
        fragmentManager,
        lifecycle
    ) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PersonalProductsFragment()
            1 -> DefaultProductsFragment()
            else -> Fragment()
        }
    }
}