package com.example.component.uicomponent

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.component.uicomponent.theme.LeasingTheme

@Composable
fun CustomDarkButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    painter: Painter? = null,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonColors(
            containerColor = LeasingTheme.colors.textSecondary,
            contentColor = LeasingTheme.colors.textInvert,
            disabledContentColor = LeasingTheme.colors.textInvert,
            disabledContainerColor = LeasingTheme.colors.textQuartenery,
        ),
    ) {
        Row {
            painter?.let { Icon(painter = painter, contentDescription = null) }

            Text(
                text = text,
                style = LeasingTheme.typography.button,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}