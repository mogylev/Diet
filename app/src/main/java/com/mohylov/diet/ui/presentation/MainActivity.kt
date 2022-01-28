package com.mohylov.diet.ui.presentation

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.mohylov.diet.R
import com.mohylov.diet.ui.presentation.main.MainScreenFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
