package com.example.component.uicomponent

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import com.example.leasing_zalyaeva_shift_2026.ui.theme.LeasingTheme

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    errorText: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    errorTrailingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                placeholder,
            )
        },
        enabled = enabled,
        readOnly = readOnly,
        isError = errorText != null,
        supportingText = {
            errorText?.let { Text(errorText) }
        },
        singleLine = singleLine,
        trailingIcon = if (errorText == null) trailingIcon else errorTrailingIcon,
        visualTransformation = visualTransformation,
        textStyle = LeasingTheme.typography.paragraph16Regular,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = LeasingTheme.colors.borderLight,
            focusedTextColor = LeasingTheme.colors.textPrimary,
            unfocusedTextColor = LeasingTheme.colors.textPrimary,
            disabledTextColor = LeasingTheme.colors.textTertiary,
            focusedContainerColor = LeasingTheme.colors.backgroundPrimary,
            unfocusedContainerColor = LeasingTheme.colors.backgroundPrimary,
            errorContainerColor = LeasingTheme.colors.backgroundPrimary,
            disabledContainerColor = LeasingTheme.colors.backgroundSecondary,
            focusedTrailingIconColor = LeasingTheme.colors.iconPrimary,
            unfocusedTrailingIconColor = LeasingTheme.colors.iconPrimary,
            errorTrailingIconColor = LeasingTheme.colors.iconPrimary,
            selectionColors = TextSelectionColors(
                handleColor = LeasingTheme.colors.borderLight,
                backgroundColor = LeasingTheme.colors.borderLight,
            ),
            focusedPlaceholderColor = LeasingTheme.colors.textQuartenery,
            unfocusedPlaceholderColor = LeasingTheme.colors.textQuartenery,
            focusedSupportingTextColor = LeasingTheme.colors.textTertiary,
            unfocusedSupportingTextColor = LeasingTheme.colors.textTertiary,
            errorSupportingTextColor = LeasingTheme.colors.textError,
            focusedBorderColor = LeasingTheme.colors.indicatorFocused,
            unfocusedBorderColor = LeasingTheme.colors.borderLight,
            disabledBorderColor = LeasingTheme.colors.borderLight,
            errorBorderColor = LeasingTheme.colors.indicatorError
        ),
        modifier = modifier
    )
}