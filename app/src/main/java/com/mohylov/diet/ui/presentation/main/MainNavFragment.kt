package com.mohylov.diet.ui.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.mohylov.diet.R
import com.mohylov.diet.ui.presentation.utils.applyInsets

class MainNavFragment : Fragment(R.layout.fragment_main_nav) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.applyInsets(top = true, bottom = true)
    }

}