package com.strone.presentation.ui.trade.composable.transaction

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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.strone.domain.util.checkFloatRegex
import com.strone.presentation.model.CryptoTransactionModel
import com.strone.presentation.state.compose.TransactionOrderTypeState
import com.strone.presentation.state.compose.TransactionTabState
import com.strone.presentation.ui.LocalAssetComposition
import com.strone.presentation.ui.LocalContainerCornerShapeComposition
import com.strone.presentation.ui.LocalOnBuyComposition
import com.strone.presentation.ui.LocalOnSellComposition
import com.strone.presentation.ui.LocalTickerComposition
import com.strone.presentation.ui.component.chip.ChipRow
import com.strone.presentation.ui.component.chip.ChipState
import com.strone.presentation.ui.component.chip.FilledChipColors
import com.strone.presentation.ui.theme.ColorBackgroundGray
import com.strone.presentation.ui.theme.ColorFall
import com.strone.presentation.ui.theme.ColorFallLight
import com.strone.presentation.ui.theme.ColorRise
import com.strone.presentation.ui.theme.ColorRiseLight
import com.strone.presentation.util.removeComma
import com.strone.presentation.util.toDisplayedKrwFormat
import java.math.BigDecimal

@Composable
fun TransactionContainer(
    modifier: Modifier,
) {

    val ticker = LocalTickerComposition.current
    val asset = LocalAssetComposition.current
    var selectedTab by remember {
        mutableStateOf(TransactionTabState.BUY)
    }
    var selectedTransactionOrderTypeIndex by remember {
        mutableIntStateOf(0)
    }
    val cryptoTransaction by remember {
        mutableStateOf(CryptoTransactionModel(ticker.code, BigDecimal.ZERO, BigDecimal.ZERO, 0))
    }
    var transactionPriceText by remember {
        mutableStateOf(cryptoTransaction.price.stripTrailingZeros().toPlainString())
    }
    var transactionAmountText by remember {
        mutableStateOf("")
    }
    var totalPriceText by remember {
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

            ChipRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                chipStates = TransactionOrderTypeState.entries.mapIndexed { index, it ->
                    ChipState(
                        text = stringResource(it.title),
                        selected = index == selectedTransactionOrderTypeIndex
                    )
                }, onChipClicked = {
                    selectedTransactionOrderTypeIndex = it
                }, chipColors = FilledChipColors(
                    selectedContainerColor = if (selectedTab == TransactionTabState.BUY) {
                        ColorRiseLight
                    } else {
                        ColorFallLight
                    },
                    selectedTextColor = if (selectedTab == TransactionTabState.BUY) {
                        ColorRise
                    } else {
                        ColorFall
                    }
                )
            )

            if (TransactionOrderTypeState.entries[selectedTransactionOrderTypeIndex] != TransactionOrderTypeState.MARKET_ORDER) {
                TransactionPriceInputRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp)
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
                        } catch (_: Exception) {
                        }
                    },
                )

                TransactionVolumeInputRow(
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
                        } catch (_: Exception) {
                        }
                    },
                    transactionPrice = cryptoTransaction.price,
                    signature = ticker.signature
                )
            }

            TransactionAmountPercentageRow(
                modifier = Modifier
                    .padding(top = 16.dp, start = 8.dp, end = 8.dp)
                    .fillMaxWidth(),
                percentages = listOf(
                    BigDecimal(10),
                    BigDecimal(25),
                    BigDecimal(50),
                    BigDecimal(100)
                ),
                onPercentageSelected = {
                    // TODO : 보유 자산 * (percentage / 100.0) / transactionPrice
                }
            )

            TransactionTotalPriceRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = ColorBackgroundGray,
                        shape = LocalContainerCornerShapeComposition.current
                    )
                    .padding(vertical = 8.dp, horizontal = 6.dp)
                    .padding(top = 4.dp),
                totalPriceText = BigDecimal(totalPriceText.ifEmpty { "0" }),
                onTotalPriceChange = {
                    val text = it.removeComma()
                    try {
                        if (text.checkFloatRegex(13, 6) || text.isEmpty())
                            totalPriceText = text
                        else if (text.startsWith("0") && text.length > 1)
                            totalPriceText = text.substring(1)
                    } catch (_: Exception) {
                    }
                }
            )

            TransactionAvailableRow(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                krw = asset?.krw?.toDisplayedKrwFormat() ?: "",
            )

            TransactionButton(
                modifier = Modifier
                    .padding(top = 2.dp)
                    .fillMaxWidth(),
                selectedTab = selectedTab,
                onBuyClick = LocalOnBuyComposition.current,
                onSellClick = LocalOnSellComposition.current,
                cryptoTransaction = cryptoTransaction
            )
        }
    }

    LaunchedEffect(key1 = selectedTransactionOrderTypeIndex) {
        if (TransactionOrderTypeState.entries[selectedTransactionOrderTypeIndex] == TransactionOrderTypeState.MARKET_ORDER) {
            transactionPriceText = ticker.tradePrice.toPlainString()
        }
    }

    LaunchedEffect(key1 = transactionAmountText) {
        cryptoTransaction.volume = BigDecimal(transactionAmountText.removeComma().ifEmpty { "0" })
        totalPriceText = (cryptoTransaction.price * cryptoTransaction.volume).toDisplayedKrwFormat()
    }

    LaunchedEffect(key1 = transactionPriceText) {
        cryptoTransaction.price = BigDecimal(transactionPriceText.removeComma().ifEmpty { "0" })
        totalPriceText = (cryptoTransaction.price * cryptoTransaction.volume).toDisplayedKrwFormat()
    }

    LaunchedEffect(key1 = totalPriceText) {
        cryptoTransaction.totalPrice = totalPriceText.removeComma().toLong()
    }
}
