package com.mohylov.diet.ui.presentation.productsManagement

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerTabStrip
import androidx.viewpager.widget.PagerTitleStrip
import com.google.android.material.tabs.TabLayoutMediator
import com.mohylov.diet.R
import com.mohylov.diet.databinding.FragmentProductsBinding
import com.mohylov.diet.ui.presentation.base.viewBinding
import com.mohylov.diet.ui.presentation.productsManagement.adapter.ProductsPagerAdapter

class ProductsFragment : Fragment(R.layout.fragment_products) {

    private val binding: FragmentProductsBinding by viewBinding(FragmentProductsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topBar.root.setBackgroundColor(requireContext().getColor(R.color.primaryColor))
        binding.topBar.root.title = getString(R.string.products_title)
        binding.productsPager.adapter = ProductsPagerAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabLayout, binding.productsPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.products_personal_tab_title)
                1 -> tab.text = getString(R.string.products_default_tab_title)
            }
        }.attach()
    }
}