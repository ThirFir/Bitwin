package com.strone.presentation.ui.trade.composable.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.strone.presentation.R
import com.strone.presentation.util.clickable
import com.strone.presentation.util.getMinChangeablePrice
import com.strone.presentation.util.toDisplayedDoubleFormat


@Composable
fun TransactionPriceInputRow(
    modifier: Modifier,
    price: Double,
    onPriceChange: (Double) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Icon(painter = painterResource(id = R.drawable.ic_remove),
            contentDescription = stringResource(
                id = R.string.action_price_minus
            ),
            modifier =
            Modifier.clickable {
                onPriceChange(price - price.getMinChangeablePrice())
            }

        )

        Text(
            modifier = Modifier,
            text = price.toDisplayedDoubleFormat(),
            fontWeight = FontWeight.Bold
        )

        Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = stringResource(
            id = R.string.action_price_plus
        ),
            modifier =
            Modifier.clickable {
                onPriceChange(price + price.getMinChangeablePrice())
            }
        )
    }
}