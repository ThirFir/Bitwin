package com.strone.presentation.ui.home.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.strone.domain.model.Ticker

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    hotTickers: List<Ticker>
) {
    if (hotTickers.isEmpty())
        return

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier.verticalScroll(scrollState)
    ) {
        TopAccTradePrice(
            modifier = Modifier,
            hotTickers = hotTickers
        )
    }
}