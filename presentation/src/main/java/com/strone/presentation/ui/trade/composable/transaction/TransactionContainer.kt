package com.strone.presentation.ui.trade.composable.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.strone.presentation.model.TickerModel
import com.strone.presentation.state.TransactionTabState
import com.strone.presentation.ui.theme.ColorBackgroundGray

val LocalContainerCornerShapeComposition = staticCompositionLocalOf {
    RoundedCornerShape(10.dp)
}

@Composable
fun TransactionContainer(
    modifier: Modifier,
    ticker: TickerModel,
) {

    var selectedTab by remember {
        mutableStateOf(TransactionTabState.BUY)
    }
    var transactionPrice by remember {
        mutableDoubleStateOf(ticker.tradePrice)
    }

    CompositionLocalProvider(LocalContainerCornerShapeComposition provides RoundedCornerShape(10.dp)) {
        Column(
            modifier = modifier
        ) {
            TransactionTypeTab(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                }
            )

            TransactionPriceInputRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .background(
                        color = ColorBackgroundGray,
                        shape = LocalContainerCornerShapeComposition.current
                    )
                    .padding(vertical = 8.dp, horizontal = 6.dp),
                price = transactionPrice,
                onPriceChange = {
                    transactionPrice = it
                }
            )
        }
    }
}
