package com.strone.presentation.ui.home.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.strone.presentation.R
import com.strone.presentation.model.TickerModel
import com.strone.presentation.ui.theme.Typography

@Composable
fun TopAccTradePrice(
    modifier: Modifier = Modifier,
    hotTickers: List<TickerModel>
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.top_acc_trade_price),
            style = Typography.titleSmall
        )

        repeat(2) { i ->
            Row(
                modifier = Modifier.padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                repeat(2) { j ->
                    val index = 2 * i + j
                    TopAccTradePriceItem(
                        modifier = Modifier.weight(1f),
                        hotTicker = hotTickers[index]
                    )
                }
            }
        }
    }
}