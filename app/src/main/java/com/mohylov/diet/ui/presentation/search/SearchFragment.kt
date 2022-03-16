package com.mohylov.diet.ui.presentation.search

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
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
import com.mohylov.diet.ui.presentation.base.BaseFragment
import com.mohylov.diet.ui.presentation.base.NavigationActions
import com.mohylov.diet.ui.presentation.base.scopedComponent
import com.mohylov.diet.ui.presentation.base.viewBinding
import com.mohylov.diet.ui.presentation.main.adapters.ProductsAdapter
import com.mohylov.diet.ui.presentation.search.entities.AmountInfo
import com.mohylov.diet.ui.presentation.utils.onTextChaged
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SearchFragment :
    BaseFragment<SearchViewState, SearchViewActions, SearchViewModel, FragmentSearchBinding>(R.layout.fragment_search) {

    @Inject
    lateinit var assistFactory: SearchViewModel.ViewModelAssistFactory

    override val binding by viewBinding(FragmentSearchBinding::bind)

    override val viewModel: SearchViewModel by viewModels { assistFactory.create(args.mealInfo) }

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
        initClickProductListener()
        binding.topBar.root.apply {
            title = getString(args.mealInfo.mealNameResId)
            setBackgroundColor(requireContext().getColor(R.color.primaryColor))
            inflateMenu(R.menu.search_menu)
            setOnMenuItemClickListener {
                viewModel.onAddictProductMenuClick()
                true
            }
            navigationIcon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                viewModel.onBackPressed()
            }
        }

        binding.searchRecycler.adapter = searchAdapter
        binding.searchInputLayout.editText?.let { editText ->
            editText.onTextChaged()
                .filter { it.isNotEmpty() && it.length >= 3 }
                .debounce(500)
                .onEach { viewModel.onSearchTextChanged(it.toString()) }
                .launchIn(viewLifecycleOwner.lifecycleScope)
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

    override fun viewStateChanged(state: SearchViewState) {
        searchAdapter.submitList(state.filteredProducts)
    }

    override fun viewActionsChanged(action: SearchViewActions) {
        super.viewActionsChanged(action)
        when (action) {
            is SearchViewActions.AmountConfirmationAction -> {
                AmountBottomSheetDialog.instance(
                    action.productId,
                    action.dedaultAmount
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