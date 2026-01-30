package com.example.feature.carDetails.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.component.uicomponent.CustomButton
import com.example.component.uicomponent.CustomHorizontalDivider
import com.example.component.uicomponent.CustomImage
import com.example.component.uicomponent.CustomOutlinedButton
import com.example.component.uicomponent.CustomTopBar
import com.example.component.uicomponent.Error
import com.example.component.uicomponent.Loading
import com.example.component.uicomponent.theme.LeasingTheme
import com.example.feature.carDetails.R
import com.example.feature.carDetails.presentation.CarDetailsViewModel
import com.example.feature.carDetails.presentation.ScreenState
import com.example.shared.car.domain.entity.Car
import com.example.shared.car.domain.entity.getCover

@Composable
fun CarDetailsScreen(
    carDetailsViewModel: CarDetailsViewModel,
    onBackClick: () -> Unit,
    onBookClick: (String) -> Unit,
) {

    val screenState by carDetailsViewModel.screenState.collectAsState()

    LaunchedEffect(Unit) {
        carDetailsViewModel.loadCar()
    }

    when (val state = screenState) {
        is ScreenState.Content -> Screen(state.car, onBackClick, onBookClick)
        ScreenState.Error -> Error(carDetailsViewModel::loadCar)
        ScreenState.Loading -> Loading()
    }
}

@Composable
private fun Screen(
    car: Car,
    onBackClick: () -> Unit,
    onBookClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CustomTopBar(
                title = stringResource(R.string.cars),
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
        containerColor = LeasingTheme.colors.backgroundPrimary,
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.exclude(WindowInsets.navigationBars)
    ) { paddingValues ->
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(start = 16.dp, end = 16.dp, top = 24.dp)
        ) {
            CustomImage(
                url = car.getCover(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            )

            CarInfoCard(car)

            CarCostCard(car)

            CustomOutlinedButton(
                text = stringResource(R.string.back),
                onClick = onBackClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            CustomButton(
                text = stringResource(R.string.book),
                onClick = { onBookClick(car.id) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
private fun CarCostCard(
    car: Car,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            stringResource(R.string.cost),
            style = LeasingTheme.typography.paragraph16Medium,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        CustomHorizontalDivider()

        Text(
            "${stringResource(R.string.total)}: ${car.price * 14}",
            style = LeasingTheme.typography.titleH3,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            stringResource(R.string.rent),
            style = LeasingTheme.typography.paragraph16Regular,
            color = LeasingTheme.colors.textSecondary,
            modifier = Modifier.padding(bottom = 32.dp)
        )
    }
}

@Composable
private fun CarInfoCard(
    car: Car,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            car.name,
            style = LeasingTheme.typography.titleH2
        )

        Text(
            stringResource(R.string.specifications),
            style = LeasingTheme.typography.paragraph16Medium,
            modifier = Modifier.padding(top = 16.dp)
        )

        CarProperty(
            name = stringResource(R.string.transmission),
            value = stringResource(car.transmission.stringId)
        )

        CustomHorizontalDivider()

        CarProperty(
            name = stringResource(R.string.steering),
            value = stringResource(car.steering.stringId)
        )

        CustomHorizontalDivider()

        CarProperty(
            name = stringResource(R.string.body_type),
            value = stringResource(car.bodyType.stringId)
        )

        CustomHorizontalDivider()

        CarProperty(
            name = stringResource(R.string.color),
            value = stringResource(car.color.stringId)
        )
    }
}

@Composable
private fun CarProperty(
    name: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.padding(vertical = 16.dp)) {
        Text(
            name,
            textAlign = TextAlign.Start,
            style = LeasingTheme.typography.paragraph16Regular,
            color = LeasingTheme.colors.textTertiary,
            modifier = Modifier.weight(1f)
        )

        Text(
            value,
            textAlign = TextAlign.Start,
            style = LeasingTheme.typography.paragraph16Regular,
            color = LeasingTheme.colors.textPrimary,
            modifier = Modifier.weight(1f)
        )
    }
}