package com.mohylov.diet.ui.presentation.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mohylov.diet.R
import com.mohylov.diet.databinding.FragmentMainBinding
import com.mohylov.diet.ui.appComponent
import com.mohylov.diet.ui.di.BaseViewModelFactory
import com.mohylov.diet.ui.di.components.mainComponent.DaggerMainScreenComponent
import com.mohylov.diet.ui.di.components.mainComponent.MainScreenComponent
import com.mohylov.diet.ui.presentation.base.scopedComponent
import com.mohylov.diet.ui.presentation.base.viewBinding
import javax.inject.Inject

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        screenComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.root.setBackgroundColor(requireContext().getColor(R.color.teal_700))
        viewModel.stateData.observe(viewLifecycleOwner) {

        }
    }
}