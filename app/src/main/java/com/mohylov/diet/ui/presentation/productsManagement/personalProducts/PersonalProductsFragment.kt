package com.mohylov.diet.ui.presentation.productsManagement.personalProducts

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.mohylov.diet.R
import com.mohylov.diet.databinding.FragmentProductsPersonalBinding
import com.mohylov.diet.ui.appComponent
import com.mohylov.diet.ui.di.BaseViewModelFactory
import com.mohylov.diet.ui.di.components.personalProductComponent.DaggerPersonalProductsComponent
import com.mohylov.diet.ui.presentation.base.BaseFragment
import com.mohylov.diet.ui.presentation.base.scopedComponent
import com.mohylov.diet.ui.presentation.base.viewBinding
import com.mohylov.diet.ui.presentation.productsManagement.personalProducts.adapters.PersonalProductsAdapter
import com.mohylov.diet.ui.presentation.utils.onTextChaged
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PersonalProductsFragment :
    BaseFragment<PersonalProductsViewState,
            PersonalProductsViewActions,
            PersonalProductsViewModel,
            FragmentProductsPersonalBinding>(R.layout.fragment_products_personal) {

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory

    private val component by scopedComponent {
        DaggerPersonalProductsComponent.factory().create(requireContext().appComponent())
    }

    private val productsAdapter = PersonalProductsAdapter()

    override val viewModel: PersonalProductsViewModel by viewModels {
        viewModelFactory
    }

    override val binding: FragmentProductsPersonalBinding by viewBinding(
        FragmentProductsPersonalBinding::bind
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
        productsAdapter.popupMenuClickFlow.onEach {
            viewModel.onPopupMenuSelected(it)
        }.flowWithLifecycle(viewLifecycleOwner.lifecycle).launchIn(lifecycleScope)
        binding.searchInput.editText?.apply {
            onTextChaged()
                .debounce(300)
                .onEach { viewModel.onFilterChanged(it.toString()) }
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .launchIn(lifecycleScope)
        }
    }

    override fun viewStateChanged(state: PersonalProductsViewState) {
        productsAdapter.submitList(state.productList)
    }
}