package com.example.component.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.component.uicomponent.theme.LeasingTheme

@Composable
fun SegmentedGroup(
    options: List<String>,
    selectedIndex: Int,
    onSelectedIndexChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    SingleChoiceSegmentedButtonRow(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(LeasingTheme.colors.backgroundSecondary)
            .padding(1.dp)
    ) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = RoundedCornerShape(14.dp),
                onClick = { onSelectedIndexChange(index) },
                selected = index == selectedIndex,
                label = { Text(label, style = LeasingTheme.typography.paragraph12Regular) },
                icon = {},
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = LeasingTheme.colors.backgroundPrimary,
                    activeContentColor = LeasingTheme.colors.textPrimary,
                    activeBorderColor = LeasingTheme.colors.backgroundPrimary,
                    inactiveContainerColor = LeasingTheme.colors.backgroundSecondary,
                    inactiveContentColor = LeasingTheme.colors.textTertiary,
                    inactiveBorderColor = LeasingTheme.colors.backgroundSecondary,
                )
            )
        }
    }
}