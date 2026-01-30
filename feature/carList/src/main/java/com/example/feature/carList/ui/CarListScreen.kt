package com.example.feature.carList.ui

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
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
import com.example.shared.car.domain.entity.getCover

@Composable
fun CarListScreen(
    onItemClick: (String) -> Unit,
    carListViewModel: CarListViewModel = hiltViewModel()
) {

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
            PullToRefreshBox(
                isRefreshing = false,
                onRefresh = {
                    carList.refresh()
                },
                state = pullToRefreshState,
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    item {
                        FiltersCard(
                            query = query,
                            onQueryChange = carListViewModel::search
                        )
                    }

                    when (carList.loadState.refresh) {
                        is LoadState.Error -> {
                            item { Error(onRetryClick = carList::retry) }
                        }

                        is LoadState.Loading -> {
                            item { Loading() }
                        }

                        else -> {
                            items(
                                carList.itemSnapshotList.items, key = { it.id }
                            ) { item ->
                                CarListItem(item, onItemClick)
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

@Composable
fun CarListItem(
    car: Car,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(car.id) })
            .padding(vertical = 8.dp)
            .height(116.dp)
    ) {
        CustomImage(
            url = car.getCover(),
            modifier = Modifier
                .weight(1f)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
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


