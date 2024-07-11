package com.strone.presentation.ui.cryptoList.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.strone.core.CryptoNamespace
import com.strone.domain.model.Ticker

@Composable
fun CryptoList(
    modifier: Modifier = Modifier,
    tickers: Map<String, Ticker>,
    searchInput: String
) {
    val listState = rememberLazyListState()
    LazyColumn(
        modifier = modifier,
        state = listState
    ) {
        items(
            items = tickers.values.toList(),
            key = { it.code }
        ) {
            if (it.signature.contains(searchInput, ignoreCase = true) ||
                CryptoNamespace.markets[it.code]?.koreanName?.contains(searchInput, ignoreCase = true) == true ||
                CryptoNamespace.markets[it.code]?.englishName?.contains(searchInput, ignoreCase = true) == true
                )
                CryptoListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    ticker = it
                )
        }
    }
}