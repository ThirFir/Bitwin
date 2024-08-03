package com.strone.data.mapper

import com.strone.data.constant.URLConstant.IMAGE_BASE_URL
import com.strone.data.response.websocket.ErrorStreamingResponse
import com.strone.data.response.websocket.OrderbookResponse
import com.strone.data.response.websocket.TickerStreamingResponse
import com.strone.data.response.websocket.UpbitStreamingResponse
import com.strone.domain.constants.CryptoConstants.EVEN
import com.strone.domain.constants.CryptoConstants.FALL
import com.strone.domain.constants.CryptoConstants.RISE
import com.strone.domain.model.Orderbook
import com.strone.domain.model.Ticker
import com.strone.domain.model.type.ChangeType
import kotlinx.coroutines.flow.Flow
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

fun Flow<UpbitStreamingResponse>.mapTicker(): Flow<Ticker> {
    return map {
        when(it) {
            is TickerStreamingResponse -> it.toTicker()
            is ErrorStreamingResponse -> throw it.exception
            else -> throw IllegalStateException("Unknown response type")
        }
    }
}

fun Flow<UpbitStreamingResponse>.mapOrderbook(): Flow<Orderbook> {
    return map {
        when(it) {
            is OrderbookResponse -> it.toOrderbook()
            is ErrorStreamingResponse -> throw it.exception
            else -> throw IllegalStateException("Unknown response type")
        }
    }
}