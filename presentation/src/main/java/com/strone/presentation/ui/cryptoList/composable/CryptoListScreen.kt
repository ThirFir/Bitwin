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
import com.strone.presentation.ui.cryptoList.viewmodel.TickerViewModel

@Composable
fun CryptoListScreen(
    modifier: Modifier = Modifier,
    viewModel: TickerViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier
    ) {
        val tickers by viewModel.tickers.collectAsStateWithLifecycle(emptyMap())
        CryptoList(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            tickers = tickers
        )
    }
}