package com.example.leasing_zalyaeva_shift_2026.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.leasing_zalyaeva_shift_2026.R
import com.example.leasing_zalyaeva_shift_2026.ui.theme.LeasingTheme

@Composable
fun BottomNavigation(
    navigationOptions: List<NavigationOptions>,
    selectedNavigationOption: NavigationOptions,
    onItemClicked: (NavigationOptions) -> Unit,
) {
    Column {
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = LeasingTheme.colors.borderLight
        )

        NavigationBar(containerColor = LeasingTheme.colors.backgroundPrimary) {
            for (option in navigationOptions) {
                NavigationBarItem(
                    selected = selectedNavigationOption == option,
                    onClick = { onItemClicked(option) },
                    icon = {
                        Icon(
                            painter = getIcon(option),
                            null
                        )
                    },
                    label = {
                        Text(text = getLabel(option), style = MaterialTheme.typography.labelMedium)
                    },
                    colors = NavigationBarItemColors(
                        selectedIconColor = LeasingTheme.colors.backgroundBrandPrimary,
                        selectedTextColor = LeasingTheme.colors.backgroundBrandPrimary,
                        selectedIndicatorColor = LeasingTheme.colors.backgroundPrimary,
                        unselectedIconColor = LeasingTheme.colors.borderMedium,
                        unselectedTextColor = LeasingTheme.colors.textQuartenery,
                        disabledIconColor = LeasingTheme.colors.borderMedium,
                        disabledTextColor = LeasingTheme.colors.textQuartenery
                    ),
                )
            }
        }
    }
}

@Composable
private fun getIcon(option: NavigationOptions): Painter =
    when (option) {
        NavigationOptions.CAR -> painterResource(R.drawable.car)

        NavigationOptions.ORDERS -> painterResource(R.drawable.time)

        NavigationOptions.PROFILE -> painterResource(R.drawable.user)
    }

@Composable
private fun getLabel(option: NavigationOptions): String = stringResource(
    when (option) {
        NavigationOptions.CAR -> R.string.avto_tab
        NavigationOptions.ORDERS -> R.string.order_tab
        NavigationOptions.PROFILE -> R.string.profile_tab
    }
)