package com.strone.presentation.ui.cryptoList.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.strone.core.CryptoNamespace
import com.strone.domain.model.Ticker
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CryptoList(
    modifier: Modifier = Modifier,
    tickers: Map<String, StateFlow<Ticker>>,
    searchInput: String
) {
    val listState = rememberLazyListState()

    val tickerList = mutableListOf<Ticker>()
    tickers.values.forEach {
        val ticker by it.collectAsStateWithLifecycle()
        tickerList.add(ticker)
    }

    LazyColumn(
        modifier = modifier,
        state = listState
    ) {
        items(
            items = tickerList
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