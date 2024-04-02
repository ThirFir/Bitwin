package com.strone.data.response

import com.squareup.moshi.Json

data class TradeResponse(
    @Json(name = "type") val type: String?,   // 타입, trade : 체결
    @Json(name = "code") val code: String?,   // 마켓 코드 ex) KRW-BTC
    @Json(name = "trade_price") val tradePrice: Double?,  // 체결 가격
    @Json(name = "trade_volume") val tradeVolume: Double?, // 체결량
    @Json(name = "ask_bid") val askBid: String?, // 매수/매도 구분, ASK : 매도 BID : 매수
    @Json(name = "prev_closing_price") val prevClosingPrice: Double?, // 전일 종가
    @Json(name = "change") val change: String?, // 전일 대비 RISE : 상승 EVEN : 보합 FALL : 하락
    @Json(name = "change_price") val changePrice: Double?, // 부호 없는 전일 대비 값
    @Json(name = "trade_date") val tradeDate: String?, // 체결 일자(UTC) yyyy-MM-dd
    @Json(name = "trade_time") val tradeTime: String?, // 체결 시각(UTC) HH:mm:ss
    @Json(name = "trade_timestamp") val tradeTimestamp: Long?, // 체결 타임스탬프
    @Json(name = "timestamp") val timestamp: Long?, // 타임스탬프
    @Json(name = "sequential_id") val sequentialId: Long?, // 체결 번호(Unique)
)