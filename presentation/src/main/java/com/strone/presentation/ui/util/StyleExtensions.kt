package com.strone.presentation.ui.util

import androidx.compose.ui.graphics.Color
import com.strone.domain.constants.CryptoConstants.FALL
import com.strone.domain.constants.CryptoConstants.RISE
import com.strone.presentation.ui.theme.ColorEven
import com.strone.presentation.ui.theme.ColorFall
import com.strone.presentation.ui.theme.ColorRise

internal fun String.getChangeColor(): Color {
    return when(this) {
        RISE -> ColorRise
        FALL -> ColorFall
        else -> ColorEven
    }
}

internal fun String.getChangeMark(): String {
    return when(this) {
        RISE -> "▲"
        FALL -> "▼"
        else -> "-"
    }
}