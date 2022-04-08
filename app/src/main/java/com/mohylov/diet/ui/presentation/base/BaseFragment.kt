package com.mohylov.diet.ui.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.mohylov.diet.R
import com.mohylov.diet.ui.presentation.base.navigation.handleNavigation

abstract class BaseFragment<VS, VA, VM, VB>(@LayoutRes resId: Int) : Fragment(resId) where
VS : BaseViewState, VA : BaseViewAction, VM : BaseViewModel<VS, VA>, VB : ViewBinding {

    abstract val viewModel: VM

    abstract val binding: VB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.stateData.observe(viewLifecycleOwner) {
            viewStateChanged(it)
        }
        viewModel.actionsData.observe(viewLifecycleOwner) {
            viewActionsChanged(it)
        }
        viewModel.infoSnackData.observe(viewLifecycleOwner) {
            showSnackBar(it)
        }
        viewModel.errorSnackData.observe(viewLifecycleOwner) {
            showSnackBar(it)
        }
        viewModel.navigationData.observe(viewLifecycleOwner) {
            navigate(it)
        }
    }

    abstract fun viewStateChanged(state: VS)

    protected open fun viewActionsChanged(action: VA) {}

    protected fun showSnackBar(@StringRes resId: Int) {
        Snackbar.make(requireView(), resId, Snackbar.LENGTH_SHORT).apply {
            setAction(R.string.ok_button_text) {}
        }.show()
    }

    protected fun navigate(navAction: NavigationActions) {
        handleNavigation(navAction)
    }
}

