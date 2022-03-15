package com.mohylov.diet.ui.presentation.productAddition

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputLayout
import com.mohylov.diet.R
import com.mohylov.diet.databinding.FragmentProductAdditionBinding
import com.mohylov.diet.ui.appComponent
import com.mohylov.diet.ui.di.BaseViewModelFactory
import com.mohylov.diet.ui.di.components.productAddition.DaggerProductAdditionComponent
import com.mohylov.diet.ui.presentation.base.scopedComponent
import com.mohylov.diet.ui.presentation.base.viewBinding
import com.mohylov.diet.ui.presentation.productAddition.entities.ProductInputData
import com.mohylov.diet.ui.presentation.utils.navigate
import com.mohylov.diet.ui.presentation.utils.text
import javax.inject.Inject

class ProductAdditionFragment : Fragment(R.layout.fragment_product_addition) {

    private val binding: FragmentProductAdditionBinding by viewBinding(
        FragmentProductAdditionBinding::bind
    )

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory

    private val viewModel: ProductAdditionViewModel by viewModels {
        viewModelFactory
    }

    private val component by scopedComponent {
        DaggerProductAdditionComponent.builder().deps(requireContext().appComponent()).build()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        binding.topBar.root.apply {
            title = resources.getString(R.string.product_title)
            setBackgroundColor(requireContext().getColor(R.color.primaryColor))

            navigationIcon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                viewModel.onBackPressed()
            }
        }
        binding.addButton.setOnClickListener {
            viewModel.onAddClick(
                ProductInputData(
                    productName = binding.productNameILayout.text(),
                    productCalories = binding.caloriesAmountILayout.text(),
                    productProteins = binding.proteinsAmountILayout.text(),
                    productFats = binding.fatsAmountILayout.text(),
                    productCarbohydrates = binding.carbohydratesAmountILayout.text(),
                )
            )
        }
    }

    private fun initObservers() {
        viewModel.navigationData.observe(viewLifecycleOwner) {
            navigate(it)
        }
        viewModel.stateData.observe(viewLifecycleOwner){
            handleState(it)
        }
    }

    private fun handleState(viewState: ProductAdditionViewState) {
        binding.productNameILayout.handleValidationState(viewState.productNameProductValidationState)
        binding.caloriesAmountILayout.handleValidationState(viewState.caloriesProductValidationState)
        binding.proteinsAmountILayout.handleValidationState(viewState.proteinsProductValidationState)
        binding.fatsAmountILayout.handleValidationState(viewState.fatsProductValidationState)
        binding.carbohydratesAmountILayout.handleValidationState(viewState.carbohydratesProductValidationState)

    }


    private fun TextInputLayout.handleValidationState(productValidationState: ProductValidationState) {
        error = when (productValidationState) {
            is ProductValidationState.EmptyField -> {
                resources.getString(R.string.error_empty_field)
            }
            is ProductValidationState.Invalid -> {
                resources.getString(productValidationState.errorResId)
            }
            else -> {
                null
            }
        }
    }

}