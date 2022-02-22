package com.mohylov.diet.ui.presentation.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mohylov.diet.R
import com.mohylov.diet.databinding.FragmentSearchBinding
import com.mohylov.diet.ui.appComponent
import com.mohylov.diet.ui.di.components.searchComponent.DaggerSearchComponent
import com.mohylov.diet.ui.presentation.base.NavigationActions
import com.mohylov.diet.ui.presentation.base.scopedComponent
import com.mohylov.diet.ui.presentation.base.viewBinding
import com.mohylov.diet.ui.presentation.main.adapters.ProductsAdapter
import com.mohylov.diet.ui.presentation.utils.onTextChaged
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SearchFragment : Fragment(R.layout.fragment_search) {

    @Inject
    lateinit var assistFactory: SearchViewModel.ViewModelAssistFactory

    private val binding by viewBinding(FragmentSearchBinding::bind)

    private val viewModel: SearchViewModel by viewModels { assistFactory.create(args.mealInfo) }

    private val component by scopedComponent {
        DaggerSearchComponent.builder().deps(requireContext().appComponent()).build()
    }

    private val args by navArgs<SearchFragmentArgs>()

    private val searchAdapter = ProductsAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initClickProductListener()
        binding.topBar.root.title = getString(args.mealInfo.mealNameResId)
        binding.topBar.root.setBackgroundColor(requireContext().getColor(R.color.primaryColor))
        binding.searchRecycler.adapter = searchAdapter
        binding.searchInputLayout.editText?.let { editText ->
            editText.onTextChaged()
                .filter { it.isNotEmpty() && it.length >= 3 }
                .debounce(500)
                .onEach { viewModel.onSearchTextChanged(it.toString()) }
                .launchIn(viewLifecycleOwner.lifecycleScope)
        }

    }

    private fun initObservers() {
        viewModel.stateData.observe(viewLifecycleOwner) {
            searchAdapter.submitList(it.filteredProducts)
        }

        viewModel.actionsData.observe(viewLifecycleOwner) {
            handleSearchActions(it)
        }

        viewModel.navigationData.observe(viewLifecycleOwner) {
            when (it) {
                is NavigationActions.PopBackStack -> {
                    findNavController().popBackStack()
                }
                is NavigationActions.NavigationAction -> {
                    findNavController().navigate(it.direction)
                }
            }
        }

        childFragmentManager.setFragmentResultListener(
            AmountBottomSheetDialog.resultInfoKey,
            viewLifecycleOwner
        ) { key, bundle ->
            bundle.getParcelable<AmountInfo>(AmountBottomSheetDialog.resultInfoKey)?.apply {
                viewModel.onProductAmountSelected(this)
            }
        }
    }

    private fun handleSearchActions(searchViewAction: SearchViewActions) {
        when (searchViewAction) {
            is SearchViewActions.AmountConfirmationAction -> {
                AmountBottomSheetDialog.instance(
                    searchViewAction.productId,
                    searchViewAction.dedaultAmount
                ).also {
                    it.show(childFragmentManager, this::class.simpleName)
                }
            }
        }
    }

    private fun initClickProductListener() {
        searchAdapter.clickFlow.flowWithLifecycle(
            viewLifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        ).onEach {
            viewModel.onProductClicked(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }


}