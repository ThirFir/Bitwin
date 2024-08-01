package com.strone.presentation.ui.trade.orderbook.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.strone.presentation.model.OrderbookModel

@Composable
fun OrderbookUnitListContainer(
    modifier: Modifier,
    orderbookUnits: List<OrderbookModel.OrderbookUnitModel>
) {
    Column(
        modifier = modifier
    ) {
        OrderbookUnitsList(modifier = Modifier.fillMaxSize(), orderbookUnits = orderbookUnits)
    }
}