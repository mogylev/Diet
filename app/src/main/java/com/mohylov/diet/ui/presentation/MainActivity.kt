package com.mohylov.diet.ui.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.commit
import com.mohylov.diet.R
import com.mohylov.diet.ui.presentation.main.MainNavFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val mainNavFragment = MainNavFragment()
            supportFragmentManager.commit {
                replace(R.id.root_container, mainNavFragment)
                setPrimaryNavigationFragment(mainNavFragment)
            }
        }
    }
}
