package com.strone.data.response.rest.candle

import com.squareup.moshi.Json

data class CandleWeekResponse(
    @Json(name = "market") val market: String?, // 마켓명
    @Json(name = "candle_date_time_utc") val candleDateTimeUtc: String?, // 캔들 기준 시각(UTC 기준) - yyyy-MM-dd'T'HH:mm:ss
    @Json(name = "candle_date_time_kst") val candleDateTimeKst: String?, // 캔들 기준 시각(KST 기준) - yyyy-MM-dd'T'HH:mm:ss
    @Json(name = "opening_price") val openingPrice: Double?, // 시가
    @Json(name = "high_price") val highPrice: Double?, // 고가
    @Json(name = "low_price") val lowPrice: Double?, // 저가
    @Json(name = "trade_price") val tradePrice: Double?, // 종가
    @Json(name = "timestamp") val timestamp: Long?, // 해당 캔들에서 마지막 틱이 저장된 시각
    @Json(name = "candle_acc_trade_price") val candleAccTradePrice: Double?, // 누적 거래 금액
    @Json(name = "candle_acc_trade_volume") val candleAccTradeVolume: Double?, // 누적 거래량
    @Json(name = "first_day_of_period") val firstDayOfPeriod: String?, // 캔들 기간의 가장 첫 날
)