package com.strone.data.response.websocket

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrderbookResponse(
    @Json(name = "type") val type: String?, // 타입, orderbook : 호가
    @Json(name = "code") val code: String?, // 마켓 코드 ex) KRW-BTC
    @Json(name = "total_ask_size") val totalAskSize: Double?, // 호가 매도 총 잔량
    @Json(name = "total_bid_size") val totalBidSize: Double?, // 호가 매수 총 잔량
    @Json(name = "orderbook_units") val orderbookUnitResps: List<OrderbookUnitResponse>?, // 호가 정보
    @Json(name = "timestamp") val timestamp: Long?, // 타임스탬프
) : UpbitStreamingResponse() {

    @JsonClass(generateAdapter = true)
    data class OrderbookUnitResponse(
        @Json(name = "ask_price") val askPrice: Double?, // 매도 호가
        @Json(name = "bid_price") val bidPrice: Double?, // 매수 호가
        @Json(name = "ask_size") val askSize: Double?, // 매도 잔량
        @Json(name = "bid_size") val bidSize: Double?, // 매수 잔량
    )
}