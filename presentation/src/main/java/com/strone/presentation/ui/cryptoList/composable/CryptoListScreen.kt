package com.strone.presentation.ui.cryptoList.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.strone.domain.model.Ticker
import com.strone.presentation.ui.cryptoList.viewmodel.TickerViewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CryptoListScreen(
    modifier: Modifier = Modifier,
    searchInput: String = "",
    tickers:  Map<String, StateFlow<Ticker>>
) {
    Column(
        modifier = modifier
    ) {
        CryptoList(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            tickers = tickers,
            searchInput = searchInput
        )
    }
}