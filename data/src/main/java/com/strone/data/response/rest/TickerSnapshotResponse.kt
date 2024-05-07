package com.strone.data.response.rest

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TickerSnapshotResponse(
    @Json(name = "market") val code: String?,    // 종목 구분 코드
    @Json(name = "trade_date") val tradeDate: String?, // 최근 거래 일자(UTC) - yyyyMMdd
    @Json(name = "trade_time") val tradeTime: String?, // 최근 거래 시각(UTC) - HHmmss
    @Json(name = "trade_date_kst") val tradeDateKst: String?, // 최근 거래 일자(KST) - yyyyMMdd
    @Json(name = "trade_time_kst") val tradeTimeKst: String?, // 최근 거래 시각(KST) - HHmmss
    @Json(name = "trade_timestamp") val tradeTimestamp: Long?, // 최근 거래 일시(UTC) - Unix timestamp
    @Json(name = "opening_price") val openingPrice: Double?, // 시가
    @Json(name = "high_price") val highPrice: Double?, // 고가
    @Json(name = "low_price") val lowPrice: Double?, // 저가
    @Json(name = "trade_price") val tradePrice: Double?, // 종가(현재가)
    @Json(name = "prev_closing_price") val prevClosingPrice: Double?, // 전일 종가(UTC 0시 기준)
    @Json(name = "change") val change: String?, // RISE : 상승, EVEN : 보합, FALL : 하락
    @Json(name = "change_price") val changePrice: Double?, // 변화액의 절대값
    @Json(name = "change_rate") val changeRate: Double?, // 변화율의 절대값
    @Json(name = "signed_change_price") val signedChangePrice: Double?, // 부호가 있는 변화액
    @Json(name = "signed_change_rate") val signedChangeRate: Double?, // 부호가 있는 변화율
    @Json(name = "trade_volume") val tradeVolume: Double?, // 가장 최근 거래량
    @Json(name = "acc_trade_price") val accTradePrice: Double?, // 누적 거래대금(UTC 0시 기준)
    @Json(name = "acc_trade_price_24h") val accTradePrice24h: Double?, // 24시간 누적 거래대금
    @Json(name = "acc_trade_volume") val accTradeVolume: Double?, // 누적 거래량(UTC 0시 기준)
    @Json(name = "acc_trade_volume_24h") val accTradeVolume24h: Double?, // 24시간 누적 거래량
    @Json(name = "highest_52_week_price") val highest52WeekPrice: Double?, // 52주 신고가
    @Json(name = "highest_52_week_date") val highest52WeekDate: String?, // 52주 신고가 달성일 - yyyy-MM-dd
    @Json(name = "lowest_52_week_price") val lowest52WeekPrice: Double?, // 52주 신저가
    @Json(name = "lowest_52_week_date") val lowest52WeekDate: String?, // 52주 신저가 달성일 - yyyy-MM-dd
    @Json(name = "timestamp") val timestamp: Long?, // 타임스탬프
)