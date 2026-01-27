package com.example.leasing_zalyaeva_shift_2026.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.leasing_zalyaeva_shift_2026.ui.theme.LeasingTheme

@Composable
fun MainScreen() {
    val backStack = rememberNavBackStack(Route.CarList)
    val selectedTab = getSelectedTab(backStack.lastOrNull())

    Scaffold(
        bottomBar = {
            if (selectedTab != null) {
                BottomNavigation(
                    navigationOptions = NavigationOptions.entries,
                    selectedNavigationOption = selectedTab,
                    onItemClicked = {
                        when (it) {
                            NavigationOptions.CAR -> {
                                backStack.clearAndAdd(Route.CarList)
                            }

                            NavigationOptions.ORDERS -> {
                                backStack.clearAndAdd(Route.Orders)
                            }

                            NavigationOptions.PROFILE -> {
                                backStack.clearAndAdd(Route.Profile)
                            }
                        }
                    }
                )
            }
        },
        containerColor = LeasingTheme.colors.backgroundPrimary,
    ) { paddingValues ->
        NavDisplay(
            backStack = backStack,
            entryProvider = entryProvider {
                entry<Route.CarList> {}

                entry<Route.Orders> { }

                entry<Route.Profile> { }
            },
            modifier = Modifier.padding(paddingValues)
        )
    }
}

private fun NavBackStack<NavKey>.clearAndAdd(key: NavKey) {
    this.clear()
    this.add(key)
}

private fun getSelectedTab(navKey: NavKey?): NavigationOptions? {
    return when (navKey) {
        is Route.CarList -> NavigationOptions.CAR
        is Route.Orders -> NavigationOptions.ORDERS
        is Route.Profile -> NavigationOptions.PROFILE
        else -> null
    }
}