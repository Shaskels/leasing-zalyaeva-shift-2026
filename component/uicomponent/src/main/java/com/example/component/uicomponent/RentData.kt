package com.example.component.uicomponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.component.uicomponent.theme.LeasingTheme

@Composable
fun RentData(
    name: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            name,
            style = LeasingTheme.typography.paragraph12Regular,
            color = LeasingTheme.colors.textTertiary
        )

        Text(
            value,
            style = LeasingTheme.typography.paragraph16Regular,
        )
    }
}