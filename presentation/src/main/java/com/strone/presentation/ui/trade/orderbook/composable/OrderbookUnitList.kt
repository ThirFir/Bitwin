package com.strone.presentation.ui.trade.orderbook.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.strone.presentation.model.OrderbookModel

@Composable
fun OrderbookUnitsList(
    modifier: Modifier,
    orderbookUnits: List<OrderbookModel.OrderbookUnitModel>
) {
    val maxOrderSize = orderbookUnits.maxOf { it.size }

    Box(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier
        ) {
            items(
                items = orderbookUnits,
                key = { it.price }
            ) {
                OrderbookUnitItem(
                    modifier = Modifier.fillMaxWidth(),
                    orderbookUnit = it,
                    sizeRatio = it.size.toFloat() / maxOrderSize.toFloat()
                )
            }
        }
    }
}
