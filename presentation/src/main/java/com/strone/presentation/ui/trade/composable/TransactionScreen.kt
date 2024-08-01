package com.strone.presentation.ui.trade.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.strone.presentation.model.OrderbookModel
import com.strone.presentation.model.TickerModel
import com.strone.presentation.ui.trade.orderbook.composable.OrderbookUnitListContainer
import com.strone.presentation.ui.trade.orderbook.composable.OrderbookUnitsList

@Composable
fun TransactionScreen(
    modifier: Modifier,
    ticker: TickerModel,
    orderbookUnits: List<OrderbookModel.OrderbookUnitModel>
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
        ) {
            OrderbookUnitListContainer(
                modifier = Modifier.weight(0.35f),
                orderbookUnits = orderbookUnits
            )
            Spacer(modifier = Modifier.weight(0.65f))
        }
    }
}
