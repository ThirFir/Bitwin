package com.strone.presentation.ui.trade.composable.transaction

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.strone.presentation.model.OrderbookModel
import com.strone.presentation.ui.trade.composable.orderbook.OrderbookUnitListContainer

@Composable
fun TransactionScreen(
    modifier: Modifier,
    orderbookUnits: List<OrderbookModel.OrderbookUnitModel>
) {
    Row(
        modifier = modifier
    ) {
        OrderbookUnitListContainer(
            modifier = Modifier.weight(0.35f),
            orderbookUnits = orderbookUnits
        )
        TransactionContainer(modifier = Modifier
            .weight(0.65f)
            .padding(start = 18.dp),
        )
    }
}
