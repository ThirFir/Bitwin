package com.strone.presentation.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp
import com.strone.presentation.model.AssetModel
import com.strone.presentation.model.CryptoTransactionModel
import com.strone.presentation.model.MarketModel
import com.strone.presentation.model.TickerModel

val LocalMarketComposition = staticCompositionLocalOf<Map<String, MarketModel>> {
    mapOf()
}

val LocalTickersComposition = compositionLocalOf<SnapshotStateList<TickerModel>> {
    mutableStateListOf()
}

val LocalTickerComposition = compositionLocalOf<TickerModel> {
    error("Ticker not found")
}

val LocalOnBuyComposition = staticCompositionLocalOf<(CryptoTransactionModel) -> Unit> {
    error("OnBuy not found")
}

val LocalOnSellComposition = staticCompositionLocalOf<(CryptoTransactionModel) -> Unit> {
    error("OnSell not found")
}

val LocalAssetComposition = compositionLocalOf<AssetModel?> {
    error("Asset not found")
}

val LocalContainerCornerShapeComposition = staticCompositionLocalOf {
    RoundedCornerShape(10.dp)
}