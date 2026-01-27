package com.example.leasing_zalyaeva_shift_2026.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.component.uicomponent.R

val interFamily = FontFamily(
    Font(R.font.inter_24pt_regular, FontWeight.W400)
)
val Typography = LeasingType(
    tabbar = TextStyle(
        fontFamily = interFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.sp
    )
)

