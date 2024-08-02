package com.strone.presentation.ui.trade.composable.transaction

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.strone.domain.constants.CryptoConstants.KRW
import com.strone.presentation.R
import com.strone.presentation.ui.component.FilledCentralTextField
import com.strone.presentation.ui.theme.ColorGraySemiDark
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun TransactionTotalPriceRow(
    modifier: Modifier,
    totalPriceText: BigDecimal,
) {
    FilledCentralTextField(
        modifier = modifier,
        readOnly = true,
        value = if (totalPriceText.compareTo(BigDecimal("0")) == 0) "" else {
            totalPriceText.setScale(0, RoundingMode.HALF_UP).toPlainString()
        },
        onValueChange = {},
        navigationIcon = { Spacer(modifier = Modifier.size(24.dp)) },
        trailingIcon = { Spacer(modifier = Modifier.size(24.dp)) },
        label = stringResource(id = R.string.total_price) + "($KRW)",
        fontSize = 16.sp,
        color = if (totalPriceText.compareTo(BigDecimal("0")) == 0) ColorGraySemiDark else Color.Black,
        softWrap = false,
        singleLine = true,
        minTextWidth = 150.dp,
        fontWeight = FontWeight.Bold
    )
}