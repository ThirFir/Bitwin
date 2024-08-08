package com.strone.presentation.ui.cryptoList.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.strone.presentation.R
import com.strone.presentation.model.TickerModel
import com.strone.presentation.ui.component.CryptoColoredText
import com.strone.presentation.ui.main.composable.LocalMarketComposition
import com.strone.presentation.ui.theme.Typography
import com.strone.presentation.ui.util.getChangeMark
import com.strone.presentation.util.toDisplayChangeRate
import com.strone.presentation.util.toDisplayedDoubleFormat

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CryptoListItem(
    modifier: Modifier = Modifier,
    ticker: TickerModel
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            modifier = Modifier
                .size(40.dp)
                .padding(end = 12.dp),
            model = LocalMarketComposition.current[ticker.code]?.imageUrl ?: "",
            contentDescription = stringResource(id = R.string.crypto_image),
        ) {
            it.diskCacheStrategy(DiskCacheStrategy.ALL)
        }
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                text = LocalMarketComposition.current[ticker.code]?.koreanName ?: "",
                style = Typography.titleSmall)
            Text(
                text = ticker.signature,
                modifier = Modifier.padding(top = 4.dp),
                style = Typography.labelMedium,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.End
        ) {
            Text(text = ticker.tradePrice.toDisplayedDoubleFormat(), style = Typography.titleSmall)
            Row(verticalAlignment = Alignment.CenterVertically) {
                CryptoColoredText(
                    text = ticker.changeRate.toDisplayChangeRate(),
                    change = ticker.change,
                    modifier = Modifier.padding(start = 8.dp)
                )
                CryptoColoredText(
                    text = ticker.change.getChangeMark(),
                    modifier = Modifier.padding(start = 4.dp),
                    style = Typography.bodySmall,
                    change = ticker.change
                )
            }

        }
        // Text(text = ticker.accTradePrice24h.toTradeVolume(), modifier = Modifier.padding(start = 8.dp))
    }
}