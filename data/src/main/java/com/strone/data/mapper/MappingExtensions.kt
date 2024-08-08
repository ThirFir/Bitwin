package com.strone.data.mapper

import com.strone.data.constant.URLConstant.IMAGE_BASE_URL
import com.strone.data.response.websocket.ErrorStreamingResponse
import com.strone.data.response.websocket.OrderbookResponse
import com.strone.data.response.websocket.TickerStreamingResponse
import com.strone.data.response.websocket.UpbitStreamingResponse
import com.strone.domain.constants.CryptoConstants.BTC
import com.strone.domain.constants.CryptoConstants.EVEN
import com.strone.domain.constants.CryptoConstants.FALL
import com.strone.domain.constants.CryptoConstants.KRW
import com.strone.domain.constants.CryptoConstants.RISE
import com.strone.domain.constants.CryptoConstants.USDT
import com.strone.domain.model.Orderbook
import com.strone.domain.model.StreamingModel
import com.strone.domain.model.Ticker
import com.strone.domain.model.type.ChangeType
import com.strone.domain.model.type.MarketType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal fun String.toSignature(): String {
    return this.split("-")[1]
}

internal fun String.getImageUrl(): String {
    return "${IMAGE_BASE_URL}${toSignature()}.png"
}

internal fun String?.toChangeType(): ChangeType {
    return when (this) {
        RISE -> ChangeType.RISE
        EVEN -> ChangeType.EVEN
        FALL -> ChangeType.FALL
        else -> ChangeType.EVEN
    }
}

internal fun String.toMarketType() : MarketType {
    if (startsWith(KRW)) return MarketType.KRW
    if (startsWith(BTC)) return MarketType.BTC
    if (startsWith(USDT)) return MarketType.USDT
    return MarketType.UNKNOWN
}

inline fun<reified R: StreamingModel> Flow<UpbitStreamingResponse>.mapStreamingResponse(): Flow<R> {
    return map {
        when(it) {
            is TickerStreamingResponse -> it.toTicker()
            is OrderbookResponse -> it.toOrderbook()
            is ErrorStreamingResponse -> throw it.exception
            else -> throw IllegalStateException("Unknown response type")
        } as R
    }
}

fun Unit.asFlow(): Flow<Unit> {
    return flow {
        emit(Unit)
    }
}