package com.example.feature.rentCar.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.component.uicomponent.CustomButton
import com.example.component.uicomponent.CustomProgressIndicator
import com.example.component.uicomponent.CustomRadioButton
import com.example.component.uicomponent.CustomTextField
import com.example.component.uicomponent.CustomTopBar
import com.example.component.uicomponent.MaskVisualTransformation
import com.example.component.uicomponent.theme.LeasingTheme
import com.example.feature.rentCar.R
import com.example.feature.rentCar.presentation.RentCarViewModel
import com.example.feature.rentCar.presentation.RentStage
import com.example.feature.rentCar.presentation.ValidationState
import com.example.shared.rent.domain.RentInfo

private const val step = 2

@Composable
fun ClientDataScreen(
    rentCarViewModel: RentCarViewModel,
    rentInfo: RentInfo,
    validationState: ValidationState
) {
    var acceptAgreement by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CustomTopBar(
                title = stringResource(R.string.client_data),
                navigationIcon = {
                    IconButton(onClick = {
                        rentCarViewModel.goToStage(
                            RentStage.CarRent(
                                ValidationState.VALID
                            )
                        )
                    }) {
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
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            CustomProgressIndicator(
                step = step,
                stepsCount = stepsCount,
            )

            UserInput(
                value = rentInfo.lastName,
                onValueChange = rentCarViewModel::setLastName,
                errorText = if (validationState == ValidationState.LAST_NAME_INVALID) stringResource(
                    R.string.last_name_validation
                ) else null,
                placeholder = stringResource(R.string.last_name)
            )

            UserInput(
                value = rentInfo.firstName,
                onValueChange = rentCarViewModel::setFirstName,
                errorText = if (validationState == ValidationState.FIRST_NAME_INVALID) stringResource(
                    R.string.first_name_validation
                ) else null,
                placeholder = stringResource(R.string.first_name)
            )

            UserInput(
                value = rentInfo.middleName ?: "",
                onValueChange = rentCarViewModel::setMiddleName,
                placeholder = stringResource(R.string.middle_name)
            )

            UserInput(
                value = rentInfo.birthDate,
                onValueChange = rentCarViewModel::setBirthDate,
                visualTransformation = MaskVisualTransformation("##.##.####"),
                errorText = if (validationState == ValidationState.BIRTH_DATE_INVALID) stringResource(
                    R.string.birth_date_validation
                ) else null,
                placeholder = stringResource(R.string.birth_date)
            )

            UserInput(
                value = rentInfo.phone,
                onValueChange = rentCarViewModel::setPhone,
                visualTransformation = MaskVisualTransformation("+7 ### ### ## ##"),
                errorText = if (validationState == ValidationState.PHONE_INVALID) stringResource(
                    R.string.phone_validation
                ) else null,
                placeholder = stringResource(R.string.phone)
            )

            UserInput(
                value = rentInfo.email,
                onValueChange = rentCarViewModel::setEmail,
                errorText = if (validationState == ValidationState.EMAIL_INVALID) stringResource(
                    R.string.email_validation
                ) else null,
                placeholder = stringResource(R.string.email)
            )

            CommentBox(
                value = rentInfo.comment ?: "",
                onValueChange = rentCarViewModel::setComment,
            )

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                CustomRadioButton(
                    selected = acceptAgreement,
                    onClick = { acceptAgreement = !acceptAgreement },
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

                Text(
                    stringResource(R.string.accept_agreement),
                    style = LeasingTheme.typography.paragraph16Medium,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            CustomButton(
                text = stringResource(R.string.next),
                onClick = rentCarViewModel::nextStage,
                enabled = acceptAgreement,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun CommentBox(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            stringResource(R.string.comment),
            style = LeasingTheme.typography.paragraph14Regular,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        CustomTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = false,
            minLines = 3,
            placeholder = stringResource(R.string.enter_extra_info),
            modifier = Modifier.fillMaxWidth()
        )
    }
}