package com.example.feature.rentCar.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.component.uicomponent.Error
import com.example.component.uicomponent.Loading
import com.example.feature.rentCar.presentation.RentCarViewModel
import com.example.feature.rentCar.presentation.RentStage
import com.example.feature.rentCar.presentation.ScreenState

const val stepsCount = 3

@Composable
fun RentCarScreen(rentCarViewModel: RentCarViewModel, onSuccess: (String) -> Unit, onBackClick: () -> Unit) {
    val screenState by rentCarViewModel.screenState.collectAsState()

    LaunchedEffect(Unit) {
        rentCarViewModel.firstStage()
    }

    when (val state = screenState) {
        is ScreenState.Content -> {
            ContentScreen(rentCarViewModel, state, onBackClick)
        }

        ScreenState.Initial -> {}
        is ScreenState.Success -> {
            onSuccess(state.rentId)
        }

        ScreenState.Error -> Error(onRetryClick = { rentCarViewModel.rentCar() })
    }
}

@Composable
private fun ContentScreen(
    rentCarViewModel: RentCarViewModel,
    screenState: ScreenState.Content,
    onBackClick: () -> Unit,
) {
    when (val state = screenState.stage) {
        RentStage.CarRent -> CarRentScreen(rentCarViewModel, screenState.rentInfo, onBackClick)
        RentStage.ClientData -> ClientDataScreen(rentCarViewModel, screenState.rentInfo)
        is RentStage.DataCheck -> DataCheckScreen(
            rentCarViewModel,
            screenState.rentInfo,
            state.state,
            onBackClick
        )

        RentStage.Loading -> Loading()
    }
}