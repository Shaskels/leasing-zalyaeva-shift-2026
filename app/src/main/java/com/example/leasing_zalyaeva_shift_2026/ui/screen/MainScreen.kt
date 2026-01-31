package com.example.leasing_zalyaeva_shift_2026.ui.screen

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.component.uicomponent.theme.LeasingTheme
import com.example.feature.carDetails.presentation.CarDetailsViewModel
import com.example.feature.carDetails.ui.CarDetailsScreen
import com.example.feature.carList.ui.CarListScreen
import com.example.feature.rentCar.presentation.RentCarViewModel
import com.example.feature.rentCar.ui.RentCarScreen
import com.example.feature.rentSuccess.presentation.RentSuccessViewModel
import com.example.feature.rentSuccess.ui.RentSuccessScreen

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
        contentWindowInsets = WindowInsets()
    ) { paddingValues ->
        NavDisplay(
            backStack = backStack,
            entryProvider = entryProvider {
                entry<Route.CarList> {
                    CarListScreen(onItemClick = {
                        backStack.add(Route.CarDetails(it))
                    })
                }

                entry<Route.CarDetails> { route ->
                    val viewModel = hiltViewModel(
                        key = route.carId,
                        creationCallback = { factory: CarDetailsViewModel.CarDetailsViewModelFactory ->
                            factory.create(route.carId)
                        }
                    )
                    CarDetailsScreen(
                        carDetailsViewModel = viewModel,
                        onBackClick = {
                            backStack.removeAt(backStack.lastIndex)
                        },
                        onBookClick = { carId ->
                            backStack.add(Route.RentCar(carId))
                        }
                    )
                }

                entry<Route.RentCar> { route ->
                    val viewModel = hiltViewModel(
                        key = route.carId,
                        creationCallback = { factory: RentCarViewModel.RentCarViewModelFactory ->
                            factory.create(route.carId)
                        }
                    )
                    RentCarScreen(
                        rentCarViewModel = viewModel,
                        onSuccess = {
                            backStack.clearAndAdd(Route.RentSuccess(it))
                        },
                        onBackClick = {
                            backStack.removeAt(backStack.lastIndex)
                        }
                    )
                }

                entry<Route.RentSuccess> { route ->
                    val viewModel = hiltViewModel(
                        key = route.rent.id,
                        creationCallback = { factory: RentSuccessViewModel.RentSuccessViewModelFactory ->
                            factory.create(route.rent.id)
                        }
                    )
                    RentSuccessScreen(
                        rentSuccessViewModel = viewModel,
                        rent = route.rent,
                        onBackClick = {
                            backStack.clearAndAdd(Route.CarList)
                        }
                    )
                }

                entry<Route.Orders> {

                }

                entry<Route.Profile> {

                }
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
        is Route.CarDetails -> NavigationOptions.CAR
        else -> null
    }
}