package com.strone.presentation.ui.trade.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.strone.presentation.R
import com.strone.presentation.model.TickerModel
import com.strone.presentation.ui.LocalMarketComposition
import com.strone.presentation.ui.component.textfield.CryptoColoredText
import com.strone.presentation.ui.theme.Typography
import com.strone.presentation.util.toDisplayChangeRate
import com.strone.presentation.util.toDisplayedDoubleFormat

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TradeTickerRow(
    modifier: Modifier,
    ticker: TickerModel
) {
    Row(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center
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
        Spacer(modifier = Modifier.weight(1f))
        GlideImage(
            modifier = Modifier.size(40.dp).padding(end = 12.dp),
            model = LocalMarketComposition.current[ticker.code]?.imageUrl,
            contentDescription = stringResource(id = R.string.crypto_image),
        )
    }
}