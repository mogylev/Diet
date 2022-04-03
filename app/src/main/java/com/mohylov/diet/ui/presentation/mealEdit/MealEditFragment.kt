package com.mohylov.diet.ui.presentation.mealEdit

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mohylov.diet.R
import com.mohylov.diet.databinding.FragmentMealEditBinding
import com.mohylov.diet.ui.appComponent
import com.mohylov.diet.ui.di.components.mealEditComponent.DaggerMealEditComponent
import com.mohylov.diet.ui.presentation.base.BaseFragment
import com.mohylov.diet.ui.presentation.base.scopedComponent
import com.mohylov.diet.ui.presentation.base.viewBinding
import com.mohylov.diet.ui.presentation.mealEdit.entities.CompleteMealProductModel
import javax.inject.Inject

class MealEditFragment :
    BaseFragment<MealEditViewState, MealEditViewActions, MealEditViewModel,FragmentMealEditBinding>(R.layout.fragment_meal_edit) {

    private val args by navArgs<MealEditFragmentArgs>()

    @Inject
    lateinit var factory: MealEditViewModel.Factory

    override val viewModel: MealEditViewModel by viewModels { factory.create(args.mealProductInfo) }

    override val binding: FragmentMealEditBinding by viewBinding (FragmentMealEditBinding::bind)

    private val component by scopedComponent {
        DaggerMealEditComponent.builder().deps(requireContext().appComponent()).build()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topBar.root.setBackgroundColor(requireContext().getColor(R.color.primaryColor))
        binding.saveButton.setOnClickListener {
            viewModel.onSaveClicked(
                binding.valueEditText.text.toString().toIntOrNull() ?: return@setOnClickListener
            )
        }
    }

    override fun viewStateChanged(state: MealEditViewState) {
        binding.productName.text = state.productName
        handleProductNutrientsInfo(state.completeMealProductModel)
    }


    private fun handleProductNutrientsInfo(completeMealProductModel: CompleteMealProductModel?) {
        completeMealProductModel?.let {
            binding.valueEditText.setText(it.amount.toString())
            binding.productDefaultInfoLayout.proteins.text =
                resources.getString(R.string.proteins, completeMealProductModel.productItem.protein)
            binding.productDefaultInfoLayout.fats.text =
                resources.getString(R.string.fats, completeMealProductModel.productItem.fats)
            binding.productDefaultInfoLayout.carbohydrates.text =
                resources.getString(
                    R.string.carbohydrates,
                    completeMealProductModel.productItem.carbohydrates
                )
            binding.productDefaultInfoLayout.calories.text =
                resources.getString(
                    R.string.calories,
                    completeMealProductModel.productItem.calories
                )
        }
    }


}