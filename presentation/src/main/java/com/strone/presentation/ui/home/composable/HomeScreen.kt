package com.strone.presentation.ui.home.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.strone.core.CryptoNamespace
import com.strone.domain.model.Ticker
import com.strone.presentation.R
import com.strone.presentation.ui.theme.ColorOutline
import com.strone.presentation.ui.theme.Typography
import com.strone.presentation.ui.util.getChangeColor
import com.strone.presentation.util.toDisplayChangeRate
import com.strone.presentation.util.toDisplayPrice

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