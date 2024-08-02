package com.strone.presentation.ui.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.strone.domain.model.type.ChangeType
import com.strone.presentation.ui.theme.ColorEven
import com.strone.presentation.ui.theme.ColorFall
import com.strone.presentation.ui.theme.ColorRise

internal fun ChangeType.getChangeColor(): Color {
    return when(this) {
        ChangeType.RISE -> ColorRise
        ChangeType.FALL -> ColorFall
        ChangeType.EVEN -> ColorEven
    }
}

internal fun ChangeType.getChangeMark(): String {
    return when(this) {
        ChangeType.RISE -> "▲"
        ChangeType.FALL -> "▼"
        ChangeType.EVEN -> "-"
    }
}
