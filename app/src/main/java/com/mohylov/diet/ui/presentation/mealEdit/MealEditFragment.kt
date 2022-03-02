package com.mohylov.diet.ui.presentation.mealEdit

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.mohylov.diet.R
import com.mohylov.diet.databinding.FragmentMealEditBinding
import com.mohylov.diet.ui.appComponent
import com.mohylov.diet.ui.di.components.mealEdit.DaggerMealEditComponent
import com.mohylov.diet.ui.presentation.base.scopedComponent
import com.mohylov.diet.ui.presentation.base.viewBinding
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
        factory.create(args.mealProductId)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}