package com.strone.presentation.ui.trade.composable.transaction

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.strone.presentation.R
import com.strone.presentation.model.CryptoTransactionModel
import com.strone.presentation.state.compose.TransactionTabState
import com.strone.presentation.ui.LocalContainerCornerShapeComposition
import com.strone.presentation.ui.theme.ColorFall
import com.strone.presentation.ui.theme.ColorRise

@Composable
fun TransactionButton(
    modifier: Modifier,
    selectedTab: TransactionTabState,
    onBuyClick: (CryptoTransactionModel) -> Unit,
    onSellClick: (CryptoTransactionModel) -> Unit,
    cryptoTransaction: CryptoTransactionModel
) {
    Card(modifier = modifier,
        shape = LocalContainerCornerShapeComposition.current,
        colors = CardDefaults.cardColors().copy(
            containerColor = if (selectedTab == TransactionTabState.BUY) ColorRise else ColorFall
        ),
        onClick = {
            if (selectedTab == TransactionTabState.BUY)
                onBuyClick(cryptoTransaction)
            else onSellClick(cryptoTransaction)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = if (selectedTab == TransactionTabState.BUY) stringResource(id = R.string.buy) else stringResource(
                    id = R.string.sell
                ),
                color = Color.White
            )
        }
    }
}