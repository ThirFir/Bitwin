package com.strone.presentation.ui.cryptoList.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.strone.presentation.ui.cryptoList.viewmodel.TickerViewModel

@Composable
fun CryptoListScreen(
    modifier: Modifier = Modifier,
    viewModel: TickerViewModel = hiltViewModel()
) {
    Column(
        //modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        val tickers by viewModel.tickers.collectAsStateWithLifecycle(emptyMap())
        CryptoList(modifier = Modifier.fillMaxSize(), tickers = tickers)
    }
}