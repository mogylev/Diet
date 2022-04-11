package com.mohylov.diet.ui.presentation.base.newBase

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class TetsFrag : Fragment() {

    private val vm: TestVm = TestVm()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.stateData.observe(viewLifecycleOwner){
            onStateUpdate(it)
        }
        vm.actionData.observe(viewLifecycleOwner){
            onViewActions(it)
        }
    }


    private fun onViewActions(action: TestViewActions) {

    }

    private fun onStateUpdate(state: TestViewState) {

    }


}