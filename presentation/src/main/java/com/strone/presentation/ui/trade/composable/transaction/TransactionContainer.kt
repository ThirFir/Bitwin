package com.strone.presentation.ui.trade.composable.transaction

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.strone.domain.util.checkFloatRegex
import com.strone.presentation.model.TickerModel
import com.strone.presentation.state.TransactionTabState
import com.strone.presentation.ui.theme.ColorBackgroundGray
import com.strone.presentation.util.removeComma
import java.math.BigDecimal

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
        mutableStateOf(ticker.tradePrice)
    }
    var transactionAmount by remember {
        mutableStateOf(BigDecimal.ZERO)
    }
    var transactionPriceText by remember {
        mutableStateOf(transactionPrice.toPlainString())
    }
    var transactionAmountText by remember {
        mutableStateOf("")
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
                priceText = transactionPriceText,
                onPriceChange = {
                    val text = it.removeComma()
                    try {
                        if (text.checkFloatRegex(13, 6) || text.isEmpty())
                            transactionPriceText = text
                        else if (text.startsWith("0") && text.length > 1)
                            transactionPriceText = text.substring(1)
                    } catch(_: Exception) { }
                },
            )

            TransactionAmountInputRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .background(
                        color = ColorBackgroundGray,
                        shape = LocalContainerCornerShapeComposition.current
                    )
                    .padding(vertical = 8.dp, horizontal = 6.dp),
                amount = transactionAmountText,
                onAmountChange = {
                    val text = it.removeComma()
                    try {
                        if (text.checkFloatRegex(13, 8) || text.isEmpty())
                            transactionAmountText = text
                        else if (text.startsWith("0") && text.length > 1)
                            transactionAmountText = text.substring(1)
                    } catch(_: Exception) { }
                },
                transactionPrice = transactionPrice,
                signature = ticker.signature
            )
        }
    }

    LaunchedEffect(key1 = transactionAmountText) {
        transactionAmount = BigDecimal(transactionAmountText.removeComma().ifEmpty { "0" })
    }

    LaunchedEffect(key1 = transactionPriceText) {
        transactionPrice = BigDecimal(transactionPriceText.removeComma().ifEmpty { "0" })
    }
}
