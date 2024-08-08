package com.strone.presentation.ui.cryptoList.composable

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.strone.domain.constants.CryptoConstants.TICKER
import com.strone.presentation.ui.LocalTickersComposition
import com.strone.presentation.ui.trade.TradeActivity

@Composable
fun CryptoList(
    modifier: Modifier = Modifier,
    searchInput: String,
    listState: LazyListState = rememberLazyListState()
) {

    val context = LocalContext.current
    val tickers = LocalTickersComposition.current
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = tickers,
            key = { it.code }
        ) {
            CryptoListItem(
                modifier = remember {
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            val intent = Intent(context, TradeActivity::class.java).apply {
                                putExtra(TICKER, it)
                            }
                            context.startActivity(intent)
                        }
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                },
                ticker = it
            )
        }
    }
}