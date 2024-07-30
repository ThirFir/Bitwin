package com.strone.presentation.ui.cryptoList.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.strone.presentation.model.TickerModel
import com.strone.presentation.state.CryptoTabState

@Composable
fun CryptoListScreen(
    modifier: Modifier = Modifier,
    searchInput: String = "",
    tickers: List<TickerModel>
) {
    var selectedTab by remember {
        mutableStateOf(CryptoTabState.ALL)
    }

    val listScrollStates = arrayListOf<LazyListState>().apply {
        repeat(CryptoTabState.entries.size) {
            add(rememberLazyListState())
        }
    }

    Column(
        modifier = modifier
    ) {
        CryptoTab(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            currentTabState = selectedTab
        ) {
            selectedTab = it
        }

        CryptoTabState.entries.forEachIndexed { i, tab ->
            if (selectedTab == tab)
                CryptoList(
                    modifier = Modifier
                        .fillMaxSize(),
                    tickers = tickers,
                    searchInput = searchInput,
                    listState = listScrollStates[i]
                )
        }
    }
}