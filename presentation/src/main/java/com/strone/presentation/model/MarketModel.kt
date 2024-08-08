package com.strone.presentation.model

import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
data class MarketModel(
    val code: String,
    val koreanName: String,
    val englishName: String,
    val marketWarning: String,
    val imageUrl: String
)
