package com.strone.presentation.mapper

import com.strone.domain.model.KakaoAuthResult
import com.strone.domain.model.Orderbook
import com.strone.domain.model.Ticker
import com.strone.presentation.model.LoginResultModel
import com.strone.presentation.model.OrderbookModel
import com.strone.presentation.model.TickerModel

fun Ticker.toTickerModel() = TickerModel(
    code = this.code,
    signature = this.signature,
    type = this.type,
    openingPrice = this.openingPrice,
    highPrice = this.highPrice,
    lowPrice = this.lowPrice,
    tradePrice = this.tradePrice,
    prevClosingPrice = this.prevClosingPrice,
    change = this.change,
    changePrice = this.changePrice,
    signedChangePrice = this.signedChangePrice,
    changeRate = this.changeRate,
    signedChangeRate = this.signedChangeRate,
    tradeVolume = this.tradeVolume,
    accTradeVolume = this.accTradeVolume,
    accTradeVolume24h = this.accTradeVolume24h,
    accTradePrice = this.accTradePrice,
    accTradePrice24h = this.accTradePrice24h,
    tradeTime = this.tradeTime,
    tradeTimestamp = this.tradeTimestamp,
    tradeDate = this.tradeDate,
    highest52WeekPrice = this.highest52WeekPrice,
    highest52WeekDate = this.highest52WeekDate,
    lowest52WeekPrice = this.lowest52WeekPrice,
    lowest52WeekDate = this.lowest52WeekDate,
    timestamp = this.timestamp,
)

fun Orderbook.OrderbookUnit.toOrderbookUnitModel() = OrderbookModel.OrderbookUnitModel(
    price = this.price,
    size = this.size,
    orderType = this.orderType,
)

fun Orderbook.toOrderbookModel() = OrderbookModel(
    code = this.code,
    totalAskSize = this.totalAskSize,
    totalBidSize = this.totalBidSize,
    orderbookUnitModels = this.orderbookUnits.map {
        it.toOrderbookUnitModel()
    }.sortedByDescending { it.price },
    timestamp = this.timestamp,
)

fun KakaoAuthResult.toLoginModel() = LoginResultModel(
    isSuccess = error == null,
)