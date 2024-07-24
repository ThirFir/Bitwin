package com.strone.presentation.ui.cryptoList.composable

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.strone.domain.constants.CryptoConstants.TICKER
import com.strone.domain.model.Ticker
import com.strone.presentation.ui.trade.TradeActivity
import com.strone.presentation.util.clickable
import com.strone.presentation.util.searched

@Composable
fun CryptoList(
    modifier: Modifier = Modifier,
    tickers: List<Ticker>,
    searchInput: String,
    listState: LazyListState = rememberLazyListState()
) {

    val context = LocalContext.current
    LazyColumn(
        modifier = modifier,
        state = listState
    ) {
        items(
            items = tickers.searched(searchInput)
        ) {
            CryptoListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val intent = Intent(context, TradeActivity::class.java).apply {
                            putExtra(TICKER, it)
                        }
                        context.startActivity(intent)
                    }.padding(vertical = 16.dp),
                ticker = it
            )
        }
    }
}