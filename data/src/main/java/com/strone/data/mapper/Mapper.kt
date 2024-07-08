package com.strone.data.mapper

import com.strone.data.response.rest.MarketResponse
import com.strone.data.response.rest.TickerSnapshotResponse
import com.strone.data.response.websocket.TickerStreamingResponse
import com.strone.domain.constants.CryptoConstants.BTC
import com.strone.domain.constants.CryptoConstants.KRW
import com.strone.domain.constants.CryptoConstants.USDT
import com.strone.domain.model.Market
import com.strone.domain.model.MarketType
import com.strone.domain.model.Ticker

fun TickerStreamingResponse.toTicker() = Ticker(
    code = this.code ?: "",
    type = this.code?.toMarketType() ?: MarketType.UNKNOWN,
    openingPrice = this.openingPrice ?: 0.0,
    highPrice = this.highPrice ?: 0.0,
    lowPrice = this.lowPrice ?: 0.0,
    tradePrice = this.tradePrice ?: 0.0,
    prevClosingPrice = this.prevClosingPrice ?: 0.0,
    change = this.change ?: "EVEN",
    changePrice = this.changePrice ?: 0.0,
    signedChangePrice = this.signedChangePrice ?: 0.0,
    changeRate = this.changeRate ?: 0.0,
    signedChangeRate = this.signedChangeRate ?: 0.0,
    tradeVolume = this.tradeVolume ?: 0.0,
    accTradeVolume = this.accTradeVolume ?: 0.0,
    accTradeVolume24h = this.accTradeVolume24h ?: 0.0,
    accTradePrice = this.accTradePrice ?: 0.0,
    accTradePrice24h = this.accTradePrice24h ?: 0.0,
    tradeTime = this.tradeTime ?: "000000",
    tradeTimestamp = this.tradeTimestamp ?: 0,
    tradeDate = this.tradeDate ?: "00000000",
    highest52WeekPrice = this.highest52WeekPrice ?: 0.0,
    highest52WeekDate = this.highest52WeekDate ?: "0000-00-00",
    lowest52WeekPrice = this.lowest52WeekPrice ?: 0.0,
    lowest52WeekDate = this.lowest52WeekDate ?: "0000-00-00",
    timestamp = this.timestamp ?: 0,
)

fun TickerSnapshotResponse.toTicker() = Ticker(
    code = this.code ?: "",
    type = this.code?.toMarketType() ?: MarketType.UNKNOWN,
    openingPrice = this.openingPrice ?: 0.0,
    highPrice = this.highPrice ?: 0.0,
    lowPrice = this.lowPrice ?: 0.0,
    tradePrice = this.tradePrice ?: 0.0,
    prevClosingPrice = this.prevClosingPrice ?: 0.0,
    change = this.change ?: "EVEN",
    changePrice = this.changePrice ?: 0.0,
    signedChangePrice = this.signedChangePrice ?: 0.0,
    changeRate = this.changeRate ?: 0.0,
    signedChangeRate = this.signedChangeRate ?: 0.0,
    tradeVolume = this.tradeVolume ?: 0.0,
    accTradeVolume = this.accTradeVolume ?: 0.0,
    accTradeVolume24h = this.accTradeVolume24h ?: 0.0,
    accTradePrice = this.accTradePrice ?: 0.0,
    accTradePrice24h = this.accTradePrice24h ?: 0.0,
    tradeTime = this.tradeTime ?: "000000",
    tradeTimestamp = this.tradeTimestamp ?: 0,
    tradeDate = this.tradeDate ?: "00000000",
    highest52WeekPrice = this.highest52WeekPrice ?: 0.0,
    highest52WeekDate = this.highest52WeekDate ?: "0000-00-00",
    lowest52WeekPrice = this.lowest52WeekPrice ?: 0.0,
    lowest52WeekDate = this.lowest52WeekDate ?: "0000-00-00",
    timestamp = this.timestamp ?: 0,
)

fun MarketResponse.toMarket() = Market(
    code = this.code ?: "",
    koreanName = this.koreanName ?: "",
    englishName = this.englishName ?: "",
    marketWarning = this.marketWarning ?: "NONE"
)

fun String.toMarketType() : MarketType {
    if (startsWith(KRW)) return MarketType.KRW
    if (startsWith(BTC)) return MarketType.BTC
    if (startsWith(USDT)) return MarketType.USDT
    return MarketType.UNKNOWN
}