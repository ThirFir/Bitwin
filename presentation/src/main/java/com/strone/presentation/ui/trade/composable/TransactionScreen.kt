package com.strone.presentation.ui.trade.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.strone.domain.model.Ticker
import com.strone.presentation.ui.component.CryptoColoredText
import com.strone.presentation.ui.theme.Typography
import com.strone.presentation.util.toDisplayChangeRate
import com.strone.presentation.util.toDisplayPrice

@Composable
fun TransactionScreen(
    modifier: Modifier,
    ticker: Ticker
) {
    Column(
        modifier = modifier
    ) {
        CryptoTickerRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp, horizontal = 20.dp),
            ticker = ticker
        )
    }
}

@Composable
fun CryptoTickerRow(
    modifier: Modifier,
    ticker: Ticker
) {
    Row(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
        ) {
            CryptoColoredText(
                text = ticker.tradePrice.toDisplayPrice(),
                change = ticker.change,
                style = Typography.titleMedium
            )
            Row(
                modifier = Modifier.padding(top = 6.dp)
            ) {
                CryptoColoredText(
                    text = ticker.signedChangePrice.toDisplayPrice(),
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