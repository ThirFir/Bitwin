package com.strone.data.mapper

import com.strone.data.response.rest.MarketResponse
import com.strone.data.response.rest.TickerSnapshotResponse
import com.strone.data.response.websocket.OrderbookResponse
import com.strone.data.response.websocket.TickerStreamingResponse
import com.strone.data.util.getImageUrl
import com.strone.data.util.toChangeType
import com.strone.data.util.toSignature
import com.strone.domain.constants.CryptoConstants.BTC
import com.strone.domain.constants.CryptoConstants.KRW
import com.strone.domain.constants.CryptoConstants.USDT
import com.strone.domain.model.Market
import com.strone.domain.model.type.MarketType
import com.strone.domain.model.type.OrderType
import com.strone.domain.model.Orderbook
import com.strone.domain.model.Ticker
import java.math.BigDecimal

fun TickerStreamingResponse.toTicker() = Ticker(
    code = this.code ?: "",
    signature = this.code?.toSignature() ?: "",
    type = this.code?.toMarketType() ?: MarketType.UNKNOWN,
    openingPrice = BigDecimal(this.openingPrice.toString()),
    highPrice = BigDecimal(this.highPrice.toString()),
    lowPrice = BigDecimal(this.lowPrice.toString()),
    tradePrice = BigDecimal(this.tradePrice.toString()),
    prevClosingPrice = BigDecimal(this.prevClosingPrice.toString()),
    change = this.change.toChangeType(),
    changePrice = BigDecimal(this.changePrice.toString()),
    signedChangePrice = BigDecimal(this.signedChangePrice.toString()),
    changeRate = BigDecimal(this.changeRate.toString()),
    signedChangeRate = BigDecimal(this.signedChangeRate.toString()),
    tradeVolume = BigDecimal(this.tradeVolume.toString()),
    accTradeVolume = BigDecimal(this.accTradeVolume.toString()),
    accTradeVolume24h = BigDecimal(this.accTradeVolume24h.toString()),
    accTradePrice = BigDecimal(this.accTradePrice.toString()),
    accTradePrice24h = BigDecimal(this.accTradePrice24h.toString()),
    tradeTime = this.tradeTime ?: "000000",
    tradeTimestamp = this.tradeTimestamp ?: 0,
    tradeDate = this.tradeDate ?: "00000000",
    highest52WeekPrice = BigDecimal(this.highest52WeekPrice.toString()),
    highest52WeekDate = this.highest52WeekDate ?: "0000-00-00",
    lowest52WeekPrice = BigDecimal(this.lowest52WeekPrice.toString()),
    lowest52WeekDate = this.lowest52WeekDate ?: "0000-00-00",
    timestamp = this.timestamp ?: 0,
)

fun TickerSnapshotResponse.toTicker() = Ticker(
    code = this.code ?: "",
    signature = this.code?.toSignature() ?: "",
    type = this.code?.toMarketType() ?: MarketType.UNKNOWN,
    openingPrice = BigDecimal(this.openingPrice.toString()),
    highPrice = BigDecimal(this.highPrice.toString()),
    lowPrice = BigDecimal(this.lowPrice.toString()),
    tradePrice = BigDecimal(this.tradePrice.toString()),
    prevClosingPrice = BigDecimal(this.prevClosingPrice.toString()),
    change = this.change.toChangeType(),
    changePrice = BigDecimal(this.changePrice.toString()),
    signedChangePrice = BigDecimal(this.signedChangePrice.toString()),
    changeRate = BigDecimal(this.changeRate.toString()),
    signedChangeRate = BigDecimal(this.signedChangeRate.toString()),
    tradeVolume = BigDecimal(this.tradeVolume.toString()),
    accTradeVolume = BigDecimal(this.accTradeVolume.toString()),
    accTradeVolume24h = BigDecimal(this.accTradeVolume24h.toString()),
    accTradePrice = BigDecimal(this.accTradePrice.toString()),
    accTradePrice24h = BigDecimal(this.accTradePrice24h.toString()),
    tradeTime = this.tradeTime ?: "000000",
    tradeTimestamp = this.tradeTimestamp ?: 0,
    tradeDate = this.tradeDate ?: "00000000",
    highest52WeekPrice = BigDecimal(this.highest52WeekPrice.toString()),
    highest52WeekDate = this.highest52WeekDate ?: "0000-00-00",
    lowest52WeekPrice = BigDecimal(this.lowest52WeekPrice.toString()),
    lowest52WeekDate = this.lowest52WeekDate ?: "0000-00-00",
    timestamp = this.timestamp ?: 0,
)

fun MarketResponse.toMarket() = Market(
    code = this.code ?: "",
    koreanName = this.koreanName ?: "",
    englishName = this.englishName ?: "",
    marketWarning = this.marketWarning ?: "NONE",
    imageUrl = code?.getImageUrl() ?: ""
)

fun OrderbookResponse.toOrderbook() = Orderbook(
    code = this.code ?: "",
    totalAskSize = BigDecimal(this.totalAskSize.toString()),
    totalBidSize = BigDecimal(this.totalBidSize.toString()),
    orderbookUnits = this.orderbookUnitResps?.flatMap { it.toOrderbookUnit() } ?: emptyList(),
    timestamp = this.timestamp ?: 0
)

fun OrderbookResponse.OrderbookUnitResponse.toOrderbookUnit() = listOf(
    Orderbook.OrderbookUnit(
        price = BigDecimal(this.askPrice.toString()),
        size = BigDecimal(this.askSize.toString()),
        orderType = OrderType.ASK
    ),
    Orderbook.OrderbookUnit(
        price = BigDecimal(this.bidPrice.toString()),
        size = BigDecimal(this.bidSize.toString()),
        orderType = OrderType.BID
    )
)

private fun String.toMarketType() : MarketType {
    if (startsWith(KRW)) return MarketType.KRW
    if (startsWith(BTC)) return MarketType.BTC
    if (startsWith(USDT)) return MarketType.USDT
    return MarketType.UNKNOWN
}