package com.example.component.uicomponent

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.leasing_zalyaeva_shift_2026.ui.theme.LeasingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    navigationIcon: @Composable (() -> Unit) = {},
    actions: @Composable (RowScope.() -> Unit) = {},
) {
    TopAppBar(
        title = {
            title?.let { Text(title, style = LeasingTheme.typography.titleH2) }
        },
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        colors = TopAppBarColors(
            containerColor = LeasingTheme.colors.backgroundPrimary,
            scrolledContainerColor = LeasingTheme.colors.backgroundPrimary,
            navigationIconContentColor = LeasingTheme.colors.indicatorLight,
            titleContentColor = LeasingTheme.colors.textPrimary,
            actionIconContentColor = LeasingTheme.colors.textPrimary,
            subtitleContentColor = LeasingTheme.colors.textPrimary
        ),
    )
}