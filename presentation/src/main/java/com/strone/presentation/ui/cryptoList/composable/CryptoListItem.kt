package com.strone.presentation.ui.cryptoList.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.strone.core.CryptoNamespace
import com.strone.domain.model.Ticker
import com.strone.presentation.util.toDisplayChangeRate
import com.strone.presentation.util.toDisplayPrice
import com.strone.presentation.util.toTradeVolume

@Composable
fun CryptoListItem(
    modifier: Modifier = Modifier,
    ticker: Ticker
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Text(text = CryptoMarkets.marketOf(ticker.code).koreanName)
        Text(text = ticker.code)
        Text(text = CryptoNamespace.markets[ticker.code]?.koreanName ?: "")
        Spacer(modifier = Modifier.weight(1f))
        Text(text = ticker.tradePrice.toDisplayPrice())
        Text(text = ticker.signedChangeRate.toDisplayChangeRate(), modifier = Modifier.padding(start = 8.dp))
        Text(text = ticker.accTradePrice24h.toTradeVolume(), modifier = Modifier.padding(start = 8.dp))
    }
}