package com.strone.presentation.ui.trade.composable.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.strone.presentation.ui.theme.ColorGray
import com.strone.presentation.util.clickable
import java.math.BigDecimal

@Composable
fun TransactionAmountPercentageRow(
    modifier: Modifier,
    percentages: List<BigDecimal>,
    onPercentageSelected: (BigDecimal) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        percentages.forEach {
            Text(
                modifier = Modifier.clickable(showRipple = false) {
                    onPercentageSelected(it)
                },
                text = "${it.toPlainString()}%",
                color = ColorGray,
                fontSize = 12.sp
            )
        }

    }
}