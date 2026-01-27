package com.example.component.uicomponent

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.leasing_zalyaeva_shift_2026.ui.theme.LeasingTheme

@Composable
fun CustomButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(50.dp),
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonColors(
            containerColor = LeasingTheme.colors.backgroundBrandPrimary,
            contentColor = LeasingTheme.colors.textInvert,
            disabledContentColor = LeasingTheme.colors.textInvert,
            disabledContainerColor = LeasingTheme.colors.backgroundBrandExtraLight,
        ),
    ) {
        Text(text = text, style = LeasingTheme.typography.button)
    }
}