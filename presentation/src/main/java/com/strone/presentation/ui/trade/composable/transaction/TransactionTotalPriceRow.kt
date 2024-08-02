package com.strone.presentation.ui.trade.composable.transaction

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.strone.domain.constants.CryptoConstants.KRW
import com.strone.presentation.R
import com.strone.presentation.ui.component.FilledCentralTextField
import com.strone.presentation.ui.theme.ColorGraySemiDark
import java.math.BigDecimal

@Composable
fun TransactionTotalPriceRow(
    modifier: Modifier,
    totalPriceText: BigDecimal,
) {
    FilledCentralTextField(
        modifier = modifier,
        readOnly = true,
        value = if (totalPriceText == BigDecimal("0.00")) "" else {
            totalPriceText.stripTrailingZeros().toPlainString()
        },
        onValueChange = {},
        navigationIcon = { Spacer(modifier = Modifier.size(24.dp)) },
        trailingIcon = { Spacer(modifier = Modifier.size(24.dp)) },
        label = stringResource(id = R.string.total_price) + "($KRW)",
        fontSize = 16.sp,
        color = if (totalPriceText == BigDecimal("0.00")) ColorGraySemiDark else Color.Black,
        softWrap = false,
        singleLine = true,
        minTextWidth = 150.dp
    )
}