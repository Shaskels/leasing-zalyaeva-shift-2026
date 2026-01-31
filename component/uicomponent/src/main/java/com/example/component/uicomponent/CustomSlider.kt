package com.example.component.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.component.uicomponent.theme.LeasingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSlider(
    valueRange: ClosedFloatingPointRange<Float>,
    sliderPosition: Float,
    onSliderPositionChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Slider(
            value = sliderPosition,
            onValueChange = onSliderPositionChange,
            valueRange = valueRange,
            track = {
                SliderDefaults.Track(
                    sliderState = it, thumbTrackGapSize = 0.dp, modifier = Modifier.height(6.dp),
                    colors = SliderDefaults.colors(
                        activeTrackColor = LeasingTheme.colors.textPrimary,
                        disabledInactiveTrackColor = LeasingTheme.colors.borderLight,
                        inactiveTrackColor = LeasingTheme.colors.borderLight,
                        disabledActiveTrackColor = LeasingTheme.colors.textPrimary,
                        activeTickColor = LeasingTheme.colors.textPrimary,
                        inactiveTickColor = LeasingTheme.colors.textPrimary,
                    )
                )
            },
            thumb = {
                Box(
                    modifier = Modifier
                        .defaultMinSize(minWidth = 30.dp, minHeight = 30.dp)
                        .clip(CircleShape)
                        .background(LeasingTheme.colors.textPrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.small_arrow_left),
                            tint = LeasingTheme.colors.textInvert,
                            contentDescription = null,
                        )
                        Icon(
                            painter = painterResource(R.drawable.small_arrow_right),
                            tint = LeasingTheme.colors.textInvert,
                            contentDescription = null
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp)
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "${sliderPosition}₽",
                style = LeasingTheme.typography.paragraph12Regular,
                color = LeasingTheme.colors.textTertiary
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "${valueRange.endInclusive}₽",
                style = LeasingTheme.typography.paragraph12Regular,
                color = LeasingTheme.colors.textTertiary
            )
        }
    }
}