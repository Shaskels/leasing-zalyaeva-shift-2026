package com.example.leasing_zalyaeva_shift_2026

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.component.uicomponent.theme.Leasingzalyaevashift2026Theme
import com.example.leasing_zalyaeva_shift_2026.ui.screen.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Leasingzalyaevashift2026Theme {
                MainScreen()
            }
        }
    }
}