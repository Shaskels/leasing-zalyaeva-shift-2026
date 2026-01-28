package com.example.component.uicomponent.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

private val DarkColorScheme = LeasingColors(
    backgroundPrimary = BackgroundPrimary,
    backgroundSecondary = BackgroundSecondary,
    backgroundTertiary = BackgroundTertiary,
    backgroundDisable = BackgroundDisable,
    borderExtraLight = BorderExtraLight,
    borderLight = BorderLight,
    borderMedium = BorderMedium,
    textInvert = TextInvert,
    textPrimary = TextPrimary,
    textSecondary = TextSecondary,
    textTertiary = TextTertiary,
    textQuartenery = TextQuartenery,
    textError = TextError,
    indicatorWhite = IndicatorWhite,
    indicatorLight = IndicatorLight,
    indicatorMedium = IndicatorMedium,
    indicatorNormal = IndicatorNormal,
    indicatorError = IndicatorError,
    indicatorAttention = IndicatorAttention,
    indicatorPositive = IndicatorPositive,
    backgroundBrand = BackgroundBrand,
    backgroundBrandPrimary = BackgroundBrandPrimary,
    backgroundHoverPrimary = BackgroundHoverPrimary,
    backgroundBrandExtraLight = BackgroundBrandExtraLight,
    textBrandDisabled = TextBrandDisabled,
    indicatorFocused = IndicatorFocused,
    indicatorFocusedAlternative = IndicatorFocusedAlternative,
    iconPrimary = IconPrimary
)

private val LightColorScheme = LeasingColors(
    backgroundPrimary = BackgroundPrimary,
    backgroundSecondary = BackgroundSecondary,
    backgroundTertiary = BackgroundTertiary,
    backgroundDisable = BackgroundDisable,
    borderExtraLight = BorderExtraLight,
    borderLight = BorderLight,
    borderMedium = BorderMedium,
    textInvert = TextInvert,
    textPrimary = TextPrimary,
    textSecondary = TextSecondary,
    textTertiary = TextTertiary,
    textQuartenery = TextQuartenery,
    textError = TextError,
    indicatorWhite = IndicatorWhite,
    indicatorLight = IndicatorLight,
    indicatorMedium = IndicatorMedium,
    indicatorNormal = IndicatorNormal,
    indicatorError = IndicatorError,
    indicatorAttention = IndicatorAttention,
    indicatorPositive = IndicatorPositive,
    backgroundBrand = BackgroundBrand,
    backgroundBrandPrimary = BackgroundBrandPrimary,
    backgroundHoverPrimary = BackgroundHoverPrimary,
    backgroundBrandExtraLight = BackgroundBrandExtraLight,
    textBrandDisabled = TextBrandDisabled,
    indicatorFocused = IndicatorFocused,
    indicatorFocusedAlternative = IndicatorFocusedAlternative,
    iconPrimary = IconPrimary
)

@Composable
fun Leasingzalyaevashift2026Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    CompositionLocalProvider(LocalLeasingType provides Typography) {
        CompositionLocalProvider(LocalLeasingColors provides colorScheme) {
            MaterialTheme(
                content = content
            )
        }
    }
}

object LeasingTheme {
    val colors: LeasingColors
        @Composable
        get() = LocalLeasingColors.current
    val typography: LeasingType
        @Composable
        get() = LocalLeasingType.current

}

@Immutable
data class LeasingColors(
    val backgroundPrimary: Color,
    val backgroundSecondary: Color,
    val backgroundTertiary: Color,
    val backgroundDisable: Color,
    val borderExtraLight: Color,
    val borderLight: Color,
    val borderMedium: Color,
    val textInvert: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val textQuartenery: Color,
    val textError: Color,
    val indicatorWhite: Color,
    val indicatorLight: Color,
    val indicatorMedium: Color,
    val indicatorNormal: Color,
    val indicatorError: Color,
    val indicatorAttention: Color,
    val indicatorPositive: Color,
    val backgroundBrand: Color,
    val backgroundBrandPrimary: Color,
    val backgroundHoverPrimary: Color,
    val backgroundBrandExtraLight: Color,
    val textBrandDisabled: Color,
    val indicatorFocused: Color,
    val indicatorFocusedAlternative: Color,
    val iconPrimary: Color,
)

data class LeasingType(
    val tabbar: TextStyle,
    val button: TextStyle,
    val paragraph16Regular: TextStyle,
    val titleH2: TextStyle,
    val paragraph16Medium: TextStyle,
    val paragraph12Regular: TextStyle,
    val paragraph14Regular: TextStyle,
)

private val LocalLeasingType = staticCompositionLocalOf<LeasingType> {
    error("No Type provided")
}
private val LocalLeasingColors = staticCompositionLocalOf<LeasingColors> {
    error("No ColorPalette provided")
}