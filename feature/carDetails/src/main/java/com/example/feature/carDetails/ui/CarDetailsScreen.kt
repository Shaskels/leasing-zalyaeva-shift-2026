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
import com.example.shared.car.domain.entity.BodyType
import com.example.shared.car.domain.entity.Car
import com.example.shared.car.domain.entity.Color
import com.example.shared.car.domain.entity.Steering
import com.example.shared.car.domain.entity.Transmission

@Composable
fun CarDetailsScreen(
    carDetailsViewModel: CarDetailsViewModel,
    onBackClick: () -> Unit
) {

    val screenState by carDetailsViewModel.screenState.collectAsState()

    LaunchedEffect(Unit) {
        carDetailsViewModel.loadCar()
    }

    when (val state = screenState) {
        is ScreenState.Content -> Screen(state.car, onBackClick)
        ScreenState.Error -> Error(carDetailsViewModel::loadCar)
        ScreenState.Loading -> Loading()
    }
}

@Composable
fun Screen(car: Car, onBackClick: () -> Unit) {
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
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(start = 16.dp, end = 16.dp, top = 24.dp)
        ) {
            CustomImage(
                url = car.media.find { it.isCover }?.url ?: car.media.first().url,
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
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
fun CarCostCard(car: Car) {
    Column {
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
fun CarInfoCard(car: Car) {
    Column {
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
            value = getTransmission(car.transmission)
        )

        CustomHorizontalDivider()

        CarProperty(
            name = stringResource(R.string.steering),
            value = getSteering(car.steering)
        )

        CustomHorizontalDivider()

        CarProperty(
            name = stringResource(R.string.body_type),
            value = getBodyType(car.bodyType)
        )

        CustomHorizontalDivider()

        CarProperty(
            name = stringResource(R.string.color),
            value = getColor(car.color)
        )
    }
}

@Composable
fun CarProperty(name: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 16.dp)) {
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

@Composable
fun getTransmission(transmission: Transmission): String {
    return when (transmission) {
        Transmission.AUTOMATIC -> stringResource(R.string.automatic)
        Transmission.MANUAL -> stringResource(R.string.manual)
    }
}

@Composable
fun getSteering(steering: Steering): String {
    return when (steering) {
        Steering.LEFT -> stringResource(R.string.left)
        Steering.RIGHT -> stringResource(R.string.right)
    }
}

@Composable
fun getBodyType(bodyType: BodyType): String {
    return when (bodyType) {
        BodyType.SEDAN -> stringResource(R.string.sedan)
        BodyType.SUV -> stringResource(R.string.suv)
        BodyType.COUPE -> stringResource(R.string.coupe)
        BodyType.HATCHBACK -> stringResource(R.string.hatchback)
        BodyType.CABRIOLET -> stringResource(R.string.cabriolet)
    }
}

@Composable
fun getColor(color: Color): String {
    return when (color) {
        Color.BLACK -> stringResource(R.string.black)
        Color.WHITE -> stringResource(R.string.white)
        Color.RED -> stringResource(R.string.red)
        Color.SILVER -> stringResource(R.string.silver)
        Color.BLUE -> stringResource(R.string.blue)
        Color.GREY -> stringResource(R.string.grey)
        Color.ORANGE -> stringResource(R.string.orange)
    }
}