package com.example.feature.rentSuccess.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.component.uicomponent.CustomButton
import com.example.component.uicomponent.CustomOutlinedButton
import com.example.component.uicomponent.CustomTopBar
import com.example.component.uicomponent.Error
import com.example.component.uicomponent.Loading
import com.example.component.uicomponent.RentData
import com.example.component.uicomponent.theme.LeasingTheme
import com.example.feature.rentSuccess.R
import com.example.feature.rentSuccess.presentation.RentSuccessViewModel
import com.example.feature.rentSuccess.presentation.ScreenState
import com.example.shared.rent.domain.Rent

@Composable
fun RentSuccessScreen(
    rentSuccessViewModel: RentSuccessViewModel,
    onBackClick: () -> Unit
) {

    val screenState = rentSuccessViewModel.screenState.collectAsState()

    LaunchedEffect(Unit) {
        rentSuccessViewModel.loadRent()
    }

    when (val state = screenState.value) {
        is ScreenState.Content -> Screen(rentSuccessViewModel, state.rent, onBackClick)
        ScreenState.Error -> Error(onRetryClick = { rentSuccessViewModel.loadRent() })
        ScreenState.Loading -> Loading()
    }

}

@Composable
private fun Screen(
    rentSuccessViewModel: RentSuccessViewModel,
    rent: Rent,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CustomTopBar(
                actions = {
                    IconButton(onClick = onBackClick) {
                        Icon(painterResource(R.drawable.cross), contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            Image(
                painterResource(R.drawable.accept),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 24.dp)
            )

            Text(
                stringResource(R.string.car_rent),
                style = LeasingTheme.typography.titleH2,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .align(Alignment.CenterHorizontally)
            )

            RentData(
                name = stringResource(R.string.car),
                value = rent.carInfo.name,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            RentData(
                name = stringResource(R.string.pickup_location),
                value = rent.pickupLocation,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            RentData(
                name = stringResource(R.string.return_location),
                value = rent.returnLocation,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            RentData(
                name = stringResource(R.string.rent_dates),
                value = rentSuccessViewModel.convertMillisToDate(rent.startDate, rent.endDate),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                stringResource(R.string.all_info_in_sms),
                style = LeasingTheme.typography.paragraph14Regular,
                color = LeasingTheme.colors.borderMedium,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            CustomOutlinedButton(
                text = stringResource(R.string.check_status),
                onClick = {},
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            )

            CustomButton(
                text = stringResource(R.string.on_main),
                onClick = onBackClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}