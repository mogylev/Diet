package com.mohylov.diet.ui.presentation.mealEdit

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mohylov.diet.R
import com.mohylov.diet.databinding.FragmentMealEditBinding
import com.mohylov.diet.ui.appComponent
import com.mohylov.diet.ui.di.components.mealEdit.DaggerMealEditComponent
import com.mohylov.diet.ui.presentation.base.NavigationActions
import com.mohylov.diet.ui.presentation.base.scopedComponent
import com.mohylov.diet.ui.presentation.base.viewBinding
import com.mohylov.diet.ui.presentation.mealEdit.entities.CompleteMealProductModel
import javax.inject.Inject

class MealEditFragment : Fragment(R.layout.fragment_meal_edit) {

    private val binding: FragmentMealEditBinding by viewBinding(FragmentMealEditBinding::bind)

    private val args by navArgs<MealEditFragmentArgs>()

    @Inject
    lateinit var factory: MealEditViewModel.Factory

    private val component by scopedComponent {
        DaggerMealEditComponent.builder().deps(requireContext().appComponent()).build()
    }

    private val viewModel: MealEditViewModel by viewModels {
        factory.create(args.mealProductInfo)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        binding.topBar.root.setBackgroundColor(requireContext().getColor(R.color.primaryColor))
        binding.saveButton.setOnClickListener {
            viewModel.onSaveClicked(
                binding.valueEditText.text.toString().toIntOrNull() ?: return@setOnClickListener
            )
        }
    }

    private fun initObservers() {
        viewModel.stateData.observe(viewLifecycleOwner) {
            binding.productName.text = it.productName
            handleProductNutrientsInfo(it.completeMealProductModel)
        }
        viewModel.navigationData.observe(viewLifecycleOwner) {
            when (it) {
                is NavigationActions.NavigationAction -> findNavController().navigate(it.direction)
                is NavigationActions.PopBackStack -> findNavController().popBackStack()
            }
        }
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