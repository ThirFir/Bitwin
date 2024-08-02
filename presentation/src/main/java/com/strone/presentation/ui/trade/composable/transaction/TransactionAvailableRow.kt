package com.strone.presentation.ui.trade.composable.transaction

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.strone.domain.constants.CryptoConstants.KRW
import com.strone.presentation.R
import com.strone.presentation.ui.theme.Typography

@Composable
fun TransactionAvailableRow(
    modifier: Modifier
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.available_balance),
            style = Typography.labelMedium
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "0 $KRW",
            style = Typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        // TODO : 보유 자산
    }
}