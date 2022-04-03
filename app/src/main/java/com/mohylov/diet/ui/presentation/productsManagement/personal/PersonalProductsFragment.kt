package com.mohylov.diet.ui.presentation.productsManagement.personal

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.mohylov.diet.R
import com.mohylov.diet.databinding.FragmentProductsPersonalBinding
import com.mohylov.diet.ui.appComponent
import com.mohylov.diet.ui.di.BaseViewModelFactory
import com.mohylov.diet.ui.di.components.personalProductComponent.DaggerPersonalProductsComponent
import com.mohylov.diet.ui.di.components.personalProductComponent.PersonalProductsComponent
import com.mohylov.diet.ui.presentation.base.BaseFragment
import com.mohylov.diet.ui.presentation.base.scopedComponent
import com.mohylov.diet.ui.presentation.base.viewBinding
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun viewStateChanged(state: PersonalProductsViewState) {
        Log.e("tag!!!", "viewStateChanged:  ${state.productList}")
    }
}