package com.mohylov.diet.ui.presentation.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mohylov.diet.R
import com.mohylov.diet.databinding.FragmentMainBinding
import com.mohylov.diet.ui.appComponent
import com.mohylov.diet.ui.di.BaseViewModelFactory
import com.mohylov.diet.ui.di.components.mainComponent.DaggerMainScreenComponent
import com.mohylov.diet.ui.di.components.mainComponent.MainScreenComponent
import com.mohylov.diet.ui.domain.mealProductsCalculator.entities.NutrtientResult
import com.mohylov.diet.ui.presentation.base.NavigationActions
import com.mohylov.diet.ui.presentation.base.scopedComponent
import com.mohylov.diet.ui.presentation.base.viewBinding
import com.mohylov.diet.ui.presentation.main.adapters.MealConcatAdapter
import com.mohylov.diet.ui.presentation.main.adapters.ProductsAdapter
import com.mohylov.diet.ui.presentation.main.adapters.adapterDelegate.DelegateAdapter
import com.mohylov.diet.ui.presentation.main.adapters.adapterDelegate.DelegateAdapterItem
import com.mohylov.diet.ui.presentation.main.adapters.entities.MealHeaderAdapterDelegate
import com.mohylov.diet.ui.presentation.main.adapters.entities.MealProductDelegateAdapter
import com.mohylov.diet.ui.presentation.utils.showDialog
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

class MainScreenFragment : Fragment(R.layout.fragment_main) {

    @Inject
    lateinit var factory: BaseViewModelFactory

    private val screenComponent: MainScreenComponent by scopedComponent {
        DaggerMainScreenComponent.builder().deps(requireContext().appComponent()).build()
    }

    private val viewModel: MainViewModel by viewModels {
        factory
    }

    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    private lateinit var mealConcatAdapter: MealConcatAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        screenComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.updateData()
            }
        }
        initializeRecyclers()
        initObservers()
    }

    private fun initObservers() {
        viewModel.stateData.observe(viewLifecycleOwner) { state ->
            mealConcatAdapter.submitList(state.mealsItems)
            handleNutrientsResult(state.nutrientsResult)
        }
        viewModel.navigationData.observe(viewLifecycleOwner) {
            when (it) {
                is NavigationActions.NavigationAction -> findNavController().navigate(it.direction)
                is NavigationActions.PopBackStack -> findNavController().popBackStack()
            }
        }
        viewModel.actionsData.observe(viewLifecycleOwner) {
            handleViewActions(it)
        }
    }

    private fun handleNutrientsResult(nutrientsResult: NutrtientResult) {
        binding.totalNutrientsBar.apply {
            totalAmount.text =
                requireContext().getString(
                    R.string.totalAmount, nutrientsResult.totalAmount
                )
            totalCalories.text =
                requireContext().getString(
                    R.string.calories, nutrientsResult.totalCalories
                )
            totalFats.text = requireContext().getString(
                R.string.fats, nutrientsResult.totalFats
            )
            totalCarbs.text = requireContext().getString(
                R.string.carbohydrates,
                nutrientsResult.totalCarbohydrates
            )
            totalProteins.text =
                requireContext().getString(R.string.proteins, nutrientsResult.totalProteins)
        }
    }

    private fun handleViewActions(action: MainScreenViewActions) {
        when (action) {
            is MainScreenViewActions.RemoveProductRequestAction -> {
                requireContext().showDialog(
                    titleResId = R.string.remove_meal_product_dialog_title,
                    positiveBtnResId = R.string.ok_button_text,
                    negativeBtnResId = R.string.cancel_button_text,
                    positiveListener = { viewModel.onRemoveProductConfirm(action.product) },
                )
            }
        }
    }

    private fun initializeRecyclers() {
        val mealHeaderAdapter = MealHeaderAdapterDelegate().apply {
            clickFlow.onEach {
                Log.e("tag!!!", "onlick FRAMGENT: ", )
                viewModel.onMealHeaderClick(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
        val mealProductsAdapter = MealProductDelegateAdapter().apply {
            longClickFlow.onEach {
                viewModel.onMealProductLongClick(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
        val spars = SparseArray<DelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>>()
        spars.put(
            0,
            mealHeaderAdapter as DelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>
        )
        spars.put(
            1,
            mealProductsAdapter as DelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>
        )
        mealConcatAdapter = MealConcatAdapter(spars)
        binding.mealsRecycler.adapter = mealConcatAdapter
    }

}
