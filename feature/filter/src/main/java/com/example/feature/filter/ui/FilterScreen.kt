package com.example.feature.filter.ui

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.component.uicomponent.CustomButton
import com.example.component.uicomponent.CustomOutlinedButton
import com.example.component.uicomponent.CustomSlider
import com.example.component.uicomponent.CustomTopBar
import com.example.component.uicomponent.DropdownMenu
import com.example.component.uicomponent.SegmentedGroup
import com.example.component.uicomponent.theme.LeasingTheme
import com.example.feature.filter.R
import com.example.feature.filter.presentation.FilterViewModel
import com.example.shared.car.domain.entity.BodyType
import com.example.shared.car.domain.entity.Brand
import com.example.shared.car.domain.entity.Steering
import com.example.shared.car.domain.entity.Transmission
import com.example.shared.filter.Filter

@Composable
fun FilterScreen(
    filterViewModel: FilterViewModel = hiltViewModel(),
    filter: Filter?,
    onBackClick: () -> Unit,
    onResetFilters: () -> Unit,
    onApplyFilters: (Filter) -> Unit
) {
    val state by filterViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        filter?.let { filterViewModel.setFilters(filter) }
    }

    Scaffold(
        topBar = {
            CustomTopBar(
                title = stringResource(R.string.cars),
                actions = {
                    IconButton(onClick = onBackClick) {
                        Icon(painter = painterResource(R.drawable.cross), contentDescription = null)
                    }
                }
            )
        },
        containerColor = LeasingTheme.colors.backgroundPrimary,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {

            DropdownFieldsBox(
                state.brand,
                state.bodyType,
                onBrandSelect = { filterViewModel.setBrand(it) },
                onBodyTypeSelect = { filterViewModel.setBodyType(it) }
            )

            SegmentedFieldsBox(
                state.steering,
                state.transmission,
                onSteeringSelect = { filterViewModel.setSteering(it) },
                onTransmissionSelect = { filterViewModel.setTransmission(it) }
            )

            Text(stringResource(R.string.cost))

            CustomSlider(
                valueRange = 3000f..10000f,
                sliderPosition = state.minPrice.toFloat(),
                onSliderPositionChange = { filterViewModel.setMinPrice(it.toInt()) },
                modifier = Modifier.padding(bottom = 24.dp)
            )

            CustomOutlinedButton(
                onClick = onResetFilters,
                text = stringResource(R.string.reset_filters),
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            )

            CustomButton(
                onClick = { onApplyFilters(filterViewModel.state.value) },
                text = stringResource(R.string.find),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
private fun DropdownFieldsBox(
    brand: Brand?,
    bodyType: BodyType?,
    onBrandSelect: (Brand) -> Unit,
    onBodyTypeSelect: (BodyType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {

        Text(
            stringResource(R.string.brand),
            style = LeasingTheme.typography.paragraph14Regular,
            modifier = Modifier.padding(bottom = 6.dp),
        )

        DropdownMenu(
            options = Brand.entries.map { it.name },
            placeholder = stringResource(R.string.brand),
            selectedIndex = Brand.entries.indexOf(brand),
            onSelect = { onBrandSelect(Brand.entries[it]) },
            modifier = Modifier.padding(bottom = 16.dp),
        )

        Text(
            stringResource(R.string.body_type),
            style = LeasingTheme.typography.paragraph14Regular,
            modifier = Modifier.padding(bottom = 6.dp),
        )

        DropdownMenu(
            options = BodyType.entries.map { stringResource(it.stringId) },
            placeholder = stringResource(R.string.body_type),
            selectedIndex = BodyType.entries.indexOf(bodyType),
            onSelect = { onBodyTypeSelect(BodyType.entries[it]) },
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Composable
private fun SegmentedFieldsBox(
    steering: Steering?,
    transmission: Transmission?,
    onSteeringSelect: (Steering?) -> Unit,
    onTransmissionSelect: (Transmission?) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            stringResource(R.string.steering),
            style = LeasingTheme.typography.paragraph14Regular,
            modifier = Modifier.padding(bottom = 6.dp),
        )

        SegmentedGroup(
            options = listOf(stringResource(R.string.any)) + Steering.entries.map {
                stringResource(
                    it.stringId
                )
            },
            selectedIndex = if (steering == null) 0 else Steering.entries.indexOf(steering) + 1,
            onSelectedIndexChange = {
                if (it == 0) onSteeringSelect(null) else
                    onSteeringSelect(Steering.entries[it - 1])
            },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            stringResource(R.string.transmission),
            style = LeasingTheme.typography.paragraph14Regular,
            modifier = Modifier.padding(bottom = 6.dp),
        )

        SegmentedGroup(
            options = listOf(stringResource(R.string.any)) + Transmission.entries.map {
                stringResource(
                    it.stringId
                )
            },
            selectedIndex = if (transmission == null) 0 else Transmission.entries.indexOf(transmission) + 1,
            onSelectedIndexChange = {
                if (it == 0) onTransmissionSelect(null) else
                    onTransmissionSelect(Transmission.entries[it - 1])
            },
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}
