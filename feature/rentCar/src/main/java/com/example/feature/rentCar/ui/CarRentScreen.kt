package com.example.feature.rentCar.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.component.uicomponent.CustomButton
import com.example.component.uicomponent.CustomDatePicker
import com.example.component.uicomponent.CustomProgressIndicator
import com.example.component.uicomponent.CustomTextField
import com.example.component.uicomponent.CustomTopBar
import com.example.component.uicomponent.theme.LeasingTheme
import com.example.feature.rentCar.R
import com.example.feature.rentCar.presentation.RentCarViewModel
import com.example.feature.rentCar.presentation.ValidationState
import com.example.shared.rent.domain.RentInfo

private const val step = 1

@Composable
fun CarRentScreen(
    rentCarViewModel: RentCarViewModel,
    rentInfo: RentInfo,
    validationState: ValidationState,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            CustomTopBar(
                title = stringResource(R.string.car_rent),
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(R.drawable.ic_left),
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
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            CustomProgressIndicator(
                step = step,
                stepsCount = stepsCount,
            )

            val days = rentCarViewModel.getRentDays(rentInfo.startDate, rentInfo.endDate)
            val date = rentCarViewModel.convertMillisToDate(rentInfo.startDate, rentInfo.endDate)
            DateInput(
                date = "$date (${pluralStringResource(R.plurals.numberOfDays, days, days)})",
                validationState = validationState,
                onDateChange = rentCarViewModel::setDates
            )

            UserInput(
                value = rentInfo.pickupLocation,
                onValueChange = rentCarViewModel::setPickupLocation,
                errorText = if (validationState == ValidationState.PICKUP_LOCATION_INVALID) stringResource(
                    R.string.pickup_location_validation
                ) else null,
                placeholder = stringResource(R.string.pickup_location)
            )

            UserInput(
                value = rentInfo.returnLocation,
                onValueChange = rentCarViewModel::setReturnLocation,
                errorText = if (validationState == ValidationState.RETURN_LOCATION_INVALID) stringResource(
                    R.string.return_location_validation
                ) else null,
                placeholder = stringResource(R.string.return_location)
            )

            Spacer(modifier = Modifier.weight(1f))

            CustomButton(
                onClick = rentCarViewModel::nextStage,
                text = stringResource(R.string.next),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun DateInput(
    date: String,
    validationState: ValidationState,
    onDateChange: (Long?, Long?) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        UserInput(
            value = date,
            onValueChange = {},
            readOnly = true,
            placeholder = stringResource(R.string.rent_dates),
            errorText = if (validationState == ValidationState.DATES_INVALID) stringResource(
                R.string.dates_validation
            ) else null,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(
                        painter = painterResource(R.drawable.calendar),
                        contentDescription = null
                    )
                }
            }
        )

        if (showDatePicker) {
            CustomDatePicker(
                onDateRangeSelected = { dates ->
                    onDateChange(dates.first, dates.second)
                },
                onDismiss = {
                    showDatePicker = false
                }
            )
        }
    }
}

@Composable
fun UserInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    errorText: String? = null,
    readOnly: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    Column(modifier = modifier) {
        Text(
            placeholder,
            style = LeasingTheme.typography.paragraph14Regular,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        CustomTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            readOnly = readOnly,
            errorText = errorText,
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            modifier = Modifier.fillMaxWidth()
        )
    }
}