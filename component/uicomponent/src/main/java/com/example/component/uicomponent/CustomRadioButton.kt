package com.example.component.uicomponent

import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.component.uicomponent.theme.LeasingTheme

@Composable
fun CustomRadioButton(selected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    RadioButton(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        colors = RadioButtonDefaults.colors(
            selectedColor = LeasingTheme.colors.backgroundBrandPrimary,
            unselectedColor = LeasingTheme.colors.borderLight,
            disabledSelectedColor = LeasingTheme.colors.borderLight,
            disabledUnselectedColor = LeasingTheme.colors.borderLight
        ),
    )
}