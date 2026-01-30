package com.example.component.uicomponent

import android.icu.util.Calendar
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.component.uicomponent.theme.LeasingTheme

@Composable
fun CustomDatePicker(
    onDateRangeSelected: (Pair<Long?, Long?>) -> Unit,
    onDismiss: () -> Unit
) {
    val dateRangePickerState = rememberDateRangePickerState(selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            val now = Calendar.getInstance().timeInMillis
            return utcTimeMillis >= now
        }
    })

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDateRangeSelected(
                        Pair(
                            dateRangePickerState.selectedStartDateMillis,
                            dateRangePickerState.selectedEndDateMillis
                        )
                    )
                    onDismiss()
                }
            ) {
                Text("OK", color = LeasingTheme.colors.textPrimary)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = LeasingTheme.colors.textPrimary)
            }
        },
        colors = DatePickerDefaults.colors(
            containerColor = LeasingTheme.colors.backgroundPrimary,
        )
    ) {
        DateRangePicker(
            state = dateRangePickerState,
            showModeToggle = false,
            title = {},
            headline = {},
            colors = DatePickerDefaults.colors(
                containerColor = LeasingTheme.colors.backgroundPrimary,
                titleContentColor = LeasingTheme.colors.textPrimary,
                headlineContentColor = LeasingTheme.colors.textPrimary,
                subheadContentColor = LeasingTheme.colors.textPrimary,
                weekdayContentColor = LeasingTheme.colors.textQuartenery,
                navigationContentColor = LeasingTheme.colors.textSecondary,
                yearContentColor = LeasingTheme.colors.textSecondary,
                disabledYearContentColor = LeasingTheme.colors.textTertiary,
                dayContentColor = LeasingTheme.colors.textPrimary,
                disabledDayContentColor = LeasingTheme.colors.borderExtraLight,
                selectedDayContentColor = LeasingTheme.colors.textPrimary,
                selectedDayContainerColor = LeasingTheme.colors.backgroundSecondary,
                todayContentColor = LeasingTheme.colors.textPrimary,
                todayDateBorderColor = LeasingTheme.colors.borderMedium,
                dayInSelectionRangeContainerColor = LeasingTheme.colors.borderMedium,
                dayInSelectionRangeContentColor = LeasingTheme.colors.textPrimary,
                dividerColor = LeasingTheme.colors.backgroundPrimary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        )
    }
}