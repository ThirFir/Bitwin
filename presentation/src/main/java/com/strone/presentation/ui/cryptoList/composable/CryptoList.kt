package com.strone.presentation.ui.cryptoList.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.strone.domain.model.Ticker

@Composable
fun CryptoList(
    modifier: Modifier = Modifier,
    tickers: Map<String, Ticker>
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
            CryptoListItem(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                ticker = it
            )
        }
    }
}