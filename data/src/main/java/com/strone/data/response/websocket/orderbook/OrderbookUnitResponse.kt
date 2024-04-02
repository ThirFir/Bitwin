package com.strone.data.response.websocket.orderbook

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrderbookUnitResponse(
    @Json(name = "ask_price") val askPrice: Double?, // 매도 호가
    @Json(name = "bid_price") val bidPrice: Double?, // 매수 호가
    @Json(name = "ask_size") val askSize: Double?, // 매도 잔량
    @Json(name = "bid_size") val bidSize: Double?, // 매수 잔량
)