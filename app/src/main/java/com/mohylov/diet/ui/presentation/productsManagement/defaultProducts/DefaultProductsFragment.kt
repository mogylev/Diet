package com.mohylov.diet.ui.presentation.productsManagement.defaultProducts

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.mohylov.diet.R
import com.mohylov.diet.databinding.FragmentProductsDefaultBinding
import com.mohylov.diet.ui.appComponent
import com.mohylov.diet.ui.di.BaseViewModelFactory
import com.mohylov.diet.ui.di.components.defaultProductsComponent.DaggerDefaultProductsComponent
import com.mohylov.diet.ui.presentation.base.BaseFragment
import com.mohylov.diet.ui.presentation.base.scopedComponent
import com.mohylov.diet.ui.presentation.base.viewBinding
import com.mohylov.diet.ui.presentation.mealsList.adapters.ProductsAdapter
import com.mohylov.diet.ui.presentation.utils.onTextChaged
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class DefaultProductsFragment : BaseFragment<DefaultProductsViewState,
        DefaultProductsViewAction,
        DefaultProductsViewModel,
        FragmentProductsDefaultBinding>(R.layout.fragment_products_default) {

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory

    private val component by scopedComponent {
        DaggerDefaultProductsComponent.factory().create(requireContext().appComponent())
    }

    private val productsAdapter = ProductsAdapter()

    override val viewModel: DefaultProductsViewModel by viewModels {
        viewModelFactory
    }

    override val binding: FragmentProductsDefaultBinding by viewBinding(
        FragmentProductsDefaultBinding::bind
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.productsRecycler.adapter = productsAdapter
        binding.searchInput.editText?.apply {
            onTextChaged()
                .debounce(300)
                .onEach { viewModel.onFilterChanged(it.toString()) }
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    override fun viewStateChanged(state: DefaultProductsViewState) {
        productsAdapter.submitList(state.products)
    }
}