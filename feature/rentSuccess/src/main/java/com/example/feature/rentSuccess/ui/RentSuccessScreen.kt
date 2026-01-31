package com.example.feature.rentSuccess.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.component.uicomponent.CustomButton
import com.example.component.uicomponent.CustomOutlinedButton
import com.example.component.uicomponent.CustomTopBar
import com.example.component.uicomponent.RentData
import com.example.component.uicomponent.theme.LeasingTheme
import com.example.feature.rentSuccess.R
import com.example.feature.rentSuccess.presentation.RentSuccessViewModel
import com.example.shared.rent.domain.Rent
import com.example.shared.rent.domain.RentStatus

@Composable
fun RentSuccessScreen(
    rentSuccessViewModel: RentSuccessViewModel,
    rent: Rent,
    onBackClick: () -> Unit
) {
    Screen(rentSuccessViewModel, rent, onBackClick)
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
        },
        containerColor = LeasingTheme.colors.backgroundPrimary,
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
                rent = rent,
                rentDate = rentSuccessViewModel.convertMillisToDate(rent.startDate, rent.endDate)
            )

            Text(
                stringResource(R.string.all_info_in_sms),
                style = LeasingTheme.typography.paragraph14Regular,
                color = LeasingTheme.colors.borderMedium,
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .align(Alignment.CenterHorizontally)
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


@Composable
private fun RentData(rent: Rent, rentDate: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {

        Row(modifier = Modifier.padding(bottom = 16.dp)) {
            val statusColor =
                if (rent.status == RentStatus.CREATED) LeasingTheme.colors.indicatorAttention else LeasingTheme.colors.indicatorPositive
            RentData(
                name = stringResource(R.string.status),
                value = stringResource(rent.status.stringId),
            )

            Box(
                modifier = Modifier
                    .size(8.dp)
                    .padding(5.dp)
                    .background(statusColor)
                    .align(Alignment.CenterVertically)
            )
        }

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
            value = rentDate,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}