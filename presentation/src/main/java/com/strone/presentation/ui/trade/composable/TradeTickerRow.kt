package com.strone.presentation.ui.trade.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.strone.presentation.model.TickerModel
import com.strone.presentation.ui.component.CryptoColoredText
import com.strone.presentation.ui.theme.Typography
import com.strone.presentation.util.toDisplayChangeRate
import com.strone.presentation.util.toDisplayedDoubleFormat

@Composable
fun TradeTickerRow(
    modifier: Modifier,
    ticker: TickerModel
) {
    Row(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
        ) {
            CryptoColoredText(
                text = ticker.tradePrice.toDisplayedDoubleFormat(),
                change = ticker.change,
                style = Typography.titleMedium
            )
            Row(
                modifier = Modifier.padding(top = 6.dp)
            ) {
                CryptoColoredText(
                    text = ticker.signedChangePrice.toDisplayedDoubleFormat(),
                    change = ticker.change,
                )

                CryptoColoredText(
                    text = ticker.signedChangeRate.toDisplayChangeRate(),
                    change = ticker.change,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    }
}