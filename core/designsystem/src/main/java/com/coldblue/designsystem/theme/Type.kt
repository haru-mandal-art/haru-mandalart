package com.coldblue.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.coldblue.designsystem.R

val orbit = FontFamily(
    Font(R.font.orbit)
)

val typography = Typography(
    headlineLarge = TextStyle(fontFamily = orbit),
    headlineMedium = TextStyle(fontFamily = orbit),
    headlineSmall = TextStyle(fontFamily = orbit),
    bodyLarge = TextStyle(fontFamily = orbit),
    bodyMedium = TextStyle(fontFamily = orbit),
    bodySmall = TextStyle(fontFamily = orbit),
    labelLarge = TextStyle(fontFamily = orbit),
    labelMedium = TextStyle(fontFamily = orbit),
    labelSmall = TextStyle(fontFamily = orbit),
    titleLarge = TextStyle(fontFamily = orbit),
    titleMedium = TextStyle(fontFamily = orbit),
    titleSmall = TextStyle(fontFamily = orbit),
    displayLarge = TextStyle(fontFamily = orbit),
    displayMedium = TextStyle(fontFamily = orbit),
    displaySmall = TextStyle(fontFamily = orbit),
)

object HmStyle{
    val logo = TextStyle(
        fontFamily = orbit,
        fontSize = 30.sp,
    )
    val headline = TextStyle(
        fontFamily = orbit,
        fontSize = 20.sp,
    )
    val title = TextStyle(
        fontFamily = orbit,
        fontSize = 16.sp,
    )
    val content = TextStyle(
        fontFamily = orbit,
        fontSize = 12.sp,
    )
    val detail = TextStyle(
        fontFamily = orbit,
        fontSize = 8.sp,
    )
    val small = TextStyle(
        fontFamily = orbit,
        fontSize = 6.sp,
    )

    val tiny = TextStyle(
        fontFamily = orbit,
        fontSize = 4.sp,
    )

}



