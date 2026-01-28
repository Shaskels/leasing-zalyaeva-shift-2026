package com.example.feature.carList.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil3.annotation.ExperimentalCoilApi
import com.example.component.uicomponent.CustomDarkButton
import com.example.component.uicomponent.CustomImage
import com.example.component.uicomponent.CustomTextField
import com.example.component.uicomponent.CustomTopBar
import com.example.component.uicomponent.Error
import com.example.component.uicomponent.Loading
import com.example.component.uicomponent.theme.LeasingTheme
import com.example.feature.carList.R
import com.example.feature.carList.presentation.CarListViewModel
import com.example.shared.car.domain.entity.Car

@Composable
fun CarListScreen(carListViewModel: CarListViewModel = hiltViewModel()) {

    val query by carListViewModel.query.collectAsState()
    val carList = carListViewModel.cars.collectAsLazyPagingItems()
    val pullToRefreshState = rememberPullToRefreshState()

    Scaffold(
        topBar = {
            CustomTopBar(
                title = stringResource(R.string.cars)
            )
        },
        containerColor = LeasingTheme.colors.backgroundPrimary,
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.exclude(WindowInsets.navigationBars)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(start = 16.dp, end = 16.dp, top = 24.dp),
        ) {
            FiltersCard(
                query = query,
                onQueryChange = carListViewModel::onQueryChange
            )

            PullToRefreshBox(
                isRefreshing = false,
                onRefresh = {
                    carList.refresh()
                },
                state = pullToRefreshState,
                modifier = Modifier.fillMaxSize()
            ) {
                when (carList.loadState.refresh) {
                    is LoadState.Error -> {
                        Error(onRetryClick = carList::retry)
                    }

                    is LoadState.Loading -> {
                        Loading()
                    }

                    else -> {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(24.dp),
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxSize()
                        ) {
                            items(
                                count = carList.itemCount, key = carList.itemKey { it.id }
                            ) { index ->
                                val item = carList[index]
                                if (item != null) {
                                    CarListItem(item)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FiltersCard(query: String, onQueryChange: (String) -> Unit) {
    Text(text = stringResource(R.string.search), style = LeasingTheme.typography.paragraph14Regular)

    CustomTextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = stringResource(R.string.search),
        modifier = Modifier
            .padding(bottom = 16.dp, top = 6.dp)
            .fillMaxWidth()
    )

    Text(
        text = stringResource(R.string.book_dates),
        style = LeasingTheme.typography.paragraph14Regular
    )

    CustomTextField(
        value = "",
        onValueChange = {},
        placeholder = stringResource(R.string.book_dates),
        enabled = false,
        modifier = Modifier
            .padding(bottom = 16.dp, top = 6.dp)
            .fillMaxWidth(),
        trailingIcon = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.calendar),
                    contentDescription = null
                )
            }
        }
    )

    CustomDarkButton(
        onClick = {},
        text = stringResource(R.string.filters),
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
        painter = painterResource(R.drawable.sliders),
    )
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CarListItem(car: Car) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(116.dp)
    ) {
        CustomImage(
            url = car.media.first().url,
            modifier = Modifier
                .weight(1f)
        )

        Column(Modifier
            .weight(1f)
            .fillMaxSize()) {
            Text(
                car.name,
                style = LeasingTheme.typography.paragraph16Medium,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                car.transmission.type,
                style = LeasingTheme.typography.paragraph12Regular,
            )

            Spacer(Modifier.weight(1f))

            Text(
                "${car.price}₽",
                style = LeasingTheme.typography.paragraph16Medium,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                "${car.price * 14}₽ ${stringResource(R.string.on_14_days)}",
                style = LeasingTheme.typography.paragraph12Regular,
            )
        }
    }
}


