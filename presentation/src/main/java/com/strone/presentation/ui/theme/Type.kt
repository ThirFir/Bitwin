package com.strone.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.strone.presentation.R

val spoqaHanSansNeo = FontFamily(
    Font(R.font.spoqa_han_sans_neo_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.spoqa_han_sans_neo_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.spoqa_han_sans_neo_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.spoqa_han_sans_neo_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.spoqa_han_sans_neo_thin, FontWeight.Thin, FontStyle.Normal)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = spoqaHanSansNeo,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = spoqaHanSansNeo,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.35.sp
    ),
    bodySmall = TextStyle(
        fontFamily = spoqaHanSansNeo,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = spoqaHanSansNeo,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = spoqaHanSansNeo,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = spoqaHanSansNeo,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = spoqaHanSansNeo,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = spoqaHanSansNeo,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleSmall = TextStyle(
        fontFamily = spoqaHanSansNeo,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    labelMedium = TextStyle(
        fontFamily = spoqaHanSansNeo,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.35.sp,
        color = ColorTextGray
    ),
    labelSmall = TextStyle(
        fontFamily = spoqaHanSansNeo,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
        color = ColorTextGray
    )
)
