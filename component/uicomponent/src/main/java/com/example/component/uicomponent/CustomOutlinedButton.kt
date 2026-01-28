package com.example.component.uicomponent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.component.uicomponent.theme.LeasingTheme

@Composable
fun CustomOutlinedButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(width = 1.dp, color = LeasingTheme.colors.borderLight),
        colors = ButtonColors(
            containerColor = Color.Transparent,
            contentColor = LeasingTheme.colors.textPrimary,
            disabledContentColor = LeasingTheme.colors.textTertiary,
            disabledContainerColor = Color.Transparent,
        ),
    ) {
        Text(text = text, style = LeasingTheme.typography.button)
    }
}