package com.mohylov.diet.ui.presentation.mealsList

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.SparseArray
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mohylov.diet.R
import com.mohylov.diet.databinding.FragmentMealsListBinding
import com.mohylov.diet.ui.appComponent
import com.mohylov.diet.ui.di.BaseViewModelFactory
import com.mohylov.diet.ui.di.components.mainComponent.DaggerMainScreenComponent
import com.mohylov.diet.ui.di.components.mainComponent.MainScreenComponent
import com.mohylov.diet.ui.domain.mealProductsCalculator.entities.NutrtientResult
import com.mohylov.diet.ui.presentation.base.BaseFragment
import com.mohylov.diet.ui.presentation.base.scopedComponent
import com.mohylov.diet.ui.presentation.base.viewBinding
import com.mohylov.diet.ui.presentation.mealsList.adapters.DelegateAdapter
import com.mohylov.diet.ui.presentation.mealsList.adapters.adapterDelegate.BaseDelegateAdapter
import com.mohylov.diet.ui.presentation.mealsList.adapters.adapterDelegate.DelegateAdapterItem
import com.mohylov.diet.ui.presentation.mealsList.entities.MealHeaderAdapterDelegate
import com.mohylov.diet.ui.presentation.mealsList.entities.MealProductDelegateAdapter
import com.mohylov.diet.ui.presentation.utils.showDialog
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class MealsListFragment : BaseFragment<MainScreenViewState,
        MainScreenViewActions,
        MealsListViewModel,
        FragmentMealsListBinding>(R.layout.fragment_meals_list) {

    private val dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")

    @Inject
    lateinit var factory: BaseViewModelFactory

    private val screenComponent: MainScreenComponent by scopedComponent {
        DaggerMainScreenComponent.builder().deps(requireContext().appComponent()).build()
    }

    override val viewModel: MealsListViewModel by viewModels { factory }

    override val binding: FragmentMealsListBinding by viewBinding(FragmentMealsListBinding::bind)

    private lateinit var delegateAdapter: DelegateAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        screenComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topBar.root.setBackgroundColor(requireContext().getColor(R.color.primaryColor))
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.updateData()
            }
        }
        initializeRecyclers()
    }

    override fun viewStateChanged(state: MainScreenViewState) {
        delegateAdapter.submitList(state.mealsItems)
        handleNutrientsResult(state.nutrientsResult)
        binding.topBar.root.title = with(state.date) {
            dateFormatter.format(ZonedDateTime.ofInstant(this, ZoneId.systemDefault()))
        }
    }

    override fun viewActionsChanged(action: MainScreenViewActions) {
        super.viewActionsChanged(action)
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


    private fun initializeRecyclers() {
        val mealHeaderAdapter = MealHeaderAdapterDelegate().apply {
            clickFlow.onEach {
                viewModel.onMealHeaderClick(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
        val mealProductsAdapter = MealProductDelegateAdapter().apply {
            longClickFlow.onEach {
                viewModel.onMealProductLongClick(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)

            clickFlow.onEach {
                viewModel.onMealProductClick(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
        val spars =
            SparseArray<BaseDelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>>()
        spars.put(
            0,
            mealHeaderAdapter as BaseDelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>
        )
        spars.put(
            1,
            mealProductsAdapter as BaseDelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>
        )
        delegateAdapter = DelegateAdapter(spars)
        binding.mealsRecycler.adapter = delegateAdapter
    }

}
