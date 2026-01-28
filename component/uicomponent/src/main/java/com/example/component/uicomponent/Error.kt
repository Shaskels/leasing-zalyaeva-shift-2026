package com.example.component.uicomponent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.component.uicomponent.theme.LeasingTheme

@Composable
fun Error(onRetryClick: () -> Unit) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                stringResource(R.string.failed_to_load),
                color = LeasingTheme.colors.textPrimary,
                style = LeasingTheme.typography.button
            )

            CustomButton(
                onClick = onRetryClick,
                text = stringResource(R.string.retry),
            )
        }
    }
}