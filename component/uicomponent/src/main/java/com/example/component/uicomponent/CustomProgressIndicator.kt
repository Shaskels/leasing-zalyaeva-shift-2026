package com.example.component.uicomponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.component.uicomponent.theme.LeasingTheme

@Composable
fun CustomProgressIndicator(step: Int, stepsCount: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.step, step, stepsCount),
            style = LeasingTheme.typography.paragraph12Regular
        )

        LinearProgressIndicator(
            progress = { step.toFloat() / stepsCount },
            color = LeasingTheme.colors.indicatorPositive,
            trackColor = LeasingTheme.colors.indicatorLight,
            gapSize = 0.dp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
