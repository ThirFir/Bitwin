package com.strone.presentation.ui.trade.composable.orderbook

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.strone.presentation.model.OrderbookModel

@Composable
fun OrderbookScreen(
    modifier: Modifier,
    orderbook: List<OrderbookModel.OrderbookUnitModel>
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
        ) {
            OrderbookUnitsList(
                modifier = Modifier,
                orderbookUnits = orderbook
            )
        }
    }
}