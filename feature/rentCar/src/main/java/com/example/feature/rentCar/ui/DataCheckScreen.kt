package com.example.feature.rentCar.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.component.uicomponent.CustomButton
import com.example.component.uicomponent.CustomProgressIndicator
import com.example.component.uicomponent.CustomTopBar
import com.example.component.uicomponent.Loading
import com.example.component.uicomponent.RentData
import com.example.component.uicomponent.theme.LeasingTheme
import com.example.feature.rentCar.R
import com.example.feature.rentCar.presentation.DataCheckState
import com.example.feature.rentCar.presentation.RentCarViewModel
import com.example.feature.rentCar.presentation.RentStage
import com.example.feature.rentCar.presentation.ValidationState
import com.example.shared.car.domain.entity.Car
import com.example.shared.rent.domain.RentInfo

private const val step = 3

@Composable
fun DataCheckScreen(
    rentCarViewModel: RentCarViewModel,
    rentInfo: RentInfo,
    state: DataCheckState,
    onBackClick: () -> Unit,
) {

    LaunchedEffect(Unit) {
        rentCarViewModel.loadCar()
    }

    when (state) {
        is DataCheckState.Content -> Screen(rentCarViewModel, rentInfo, state.car, onBackClick)
        DataCheckState.Loading -> Loading()
    }
}

@Composable
private fun Screen(
    rentCarViewModel: RentCarViewModel,
    rentInfo: RentInfo,
    car: Car,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            CustomTopBar(
                title = stringResource(R.string.data_check),
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(R.drawable.cross),
                            contentDescription = null
                        )
                    }
                }
            )
        },
        containerColor = LeasingTheme.colors.backgroundPrimary
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            CustomProgressIndicator(
                step = step,
                stepsCount = stepsCount,
            )

            DataCard(
                headline = stringResource(R.string.rent_data),
                onEditClick = { rentCarViewModel.goToStage(RentStage.CarRent(ValidationState.VALID)) },
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    RentData(
                        name = stringResource(R.string.car),
                        value = car.name
                    )

                    RentData(
                        name = stringResource(R.string.rent_dates),
                        value = rentCarViewModel.convertMillisToDate(
                            rentInfo.startDate,
                            rentInfo.endDate
                        ),
                    )

                    RentData(
                        name = stringResource(R.string.pickup_location),
                        value = rentInfo.pickupLocation
                    )

                    RentData(
                        name = stringResource(R.string.return_location),
                        value = rentInfo.returnLocation,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )
                }
            }

            DataCard(
                headline = stringResource(R.string.user_data),
                onEditClick = { rentCarViewModel.goToStage(RentStage.ClientData(ValidationState.VALID)) },
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    RentData(
                        name = stringResource(R.string.fio),
                        value = "${rentInfo.lastName} ${rentInfo.firstName} ${rentInfo.middleName ?: ""}"
                    )

                    RentData(
                        name = stringResource(R.string.birth_date),
                        value = rentCarViewModel.birthDateToISODate( rentInfo.birthDate) ?: "",
                    )

                    RentData(
                        name = stringResource(R.string.phone),
                        value = "+7" + rentInfo.phone
                    )

                    RentData(
                        name = stringResource(R.string.email),
                        value = rentInfo.email
                    )

                    RentData(
                        name = stringResource(R.string.comment),
                        value = rentInfo.comment ?: "",
                        modifier = Modifier.padding(bottom = 24.dp)
                    )
                }
            }

            CostBox(
                price = car.price,
                days = rentCarViewModel.getRentDays(rentInfo.startDate, rentInfo.endDate),
                dates = rentCarViewModel.convertMillisToDate(rentInfo.startDate, rentInfo.endDate),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            CustomButton(
                text = stringResource(R.string.form),
                onClick = rentCarViewModel::rentCar,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun CostBox(
    price: Int,
    days: Int,
    dates: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(vertical = 12.dp)) {
        Text(
            "${stringResource(R.string.total)}: ${price * days}₽",
            style = LeasingTheme.typography.titleH3,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            "$dates (${pluralStringResource(R.plurals.numberOfDays, days, days)})",
            style = LeasingTheme.typography.paragraph16Regular,
            color = LeasingTheme.colors.textSecondary
        )
    }
}

@Composable
private fun DataCard(
    headline: String,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit)
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardColors(
            containerColor = LeasingTheme.colors.backgroundSecondary,
            contentColor = LeasingTheme.colors.textPrimary,
            disabledContainerColor = LeasingTheme.colors.backgroundSecondary,
            disabledContentColor = LeasingTheme.colors.textPrimary
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(bottom = 16.dp, top = 24.dp, start = 16.dp, end = 16.dp)) {
            Text(
                headline,
                style = LeasingTheme.typography.paragraph16Medium,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Spacer(Modifier.weight(1f))

            IconButton(
                onClick = onEditClick,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(painterResource(R.drawable.edit), contentDescription = null)
            }
        }

        content()
    }
}