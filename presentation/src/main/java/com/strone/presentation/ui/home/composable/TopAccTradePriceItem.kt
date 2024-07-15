package com.strone.presentation.ui.home.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.strone.core.CryptoNamespace
import com.strone.domain.model.Ticker
import com.strone.presentation.R
import com.strone.presentation.ui.component.BitwinOutlinedCard
import com.strone.presentation.ui.theme.ColorOutline
import com.strone.presentation.ui.theme.Typography
import com.strone.presentation.ui.util.getChangeColor
import com.strone.presentation.util.toDisplayChangeRate
import com.strone.presentation.util.toDisplayPrice

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TopAccTradePriceItem(
    modifier: Modifier = Modifier,
    hotTicker: Ticker
) {
    BitwinOutlinedCard(
        modifier = modifier,
        onClick = {

        },
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            GlideImage(
                modifier = Modifier
                    .size(40.dp),
                model = CryptoNamespace.markets[hotTicker.code]?.imageUrl,
                contentDescription = stringResource(
                    id = R.string.crypto_image
                )
            )

            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = hotTicker.changeRate.toDisplayChangeRate(),
                color = hotTicker.change.getChangeColor(),
                style = Typography.titleMedium
            )

            Row(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(
                    text = hotTicker.code,
                    style = Typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = hotTicker.tradePrice.toDisplayPrice(),
                    style = Typography.labelMedium
                )
            }
        }
    }
}