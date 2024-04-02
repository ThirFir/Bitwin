package com.strone.data.response

import com.squareup.moshi.Json

data class TickerResponse(
    @Json(name = "type") val type: String?,   // 타입, ticker : 현재가
    @Json(name = "code") var code: String?,   // 마켓 코드 ex) KRW-BTC   웹소켓 전용
    @Json(name = "market") val market: String?, // code와 같음, REST API 전용
    @Json(name = "opening_price") val openingPrice: Double?,  // 시가
    @Json(name = "high_price") val highPrice: Double?,        // 고가
    @Json(name = "low_price") val lowPrice: Double?,      // 저가
    @Json(name = "trade_price") val tradePrice: Double?,  // 현재가
    @Json(name = "prev_closing_price") val prevClosingPrice: Double?, // 전일 종가
    @Json(name = "change") val change: String?, // 전일 대비 RISE : 상승 EVEN : 보합 FALL : 하락
    @Json(name = "change_price") val changePrice: Double?, // 부호 없는 전일 대비 값
    @Json(name = "signed_change_price") val signedChangePrice: Double?, // 전일 대비 값
    @Json(name = "change_rate") val changeRate: Double?, // 부호 없는 전일 대비 등락율
    @Json(name = "signed_change_rate") val signedChangeRate: Double?, // 전일 대비 등락율
    @Json(name = "trade_volume") val tradeVolume: Double?, // 가장 최근 거래량
    @Json(name = "acc_trade_volume") val accTradeVolume: Double?, // 누적 거래량 (UTC 0시 기준)
    @Json(name = "acc_trade_volume_24h") val accTradeVolume24h: Double?, // 24시간 누적 거래량
    @Json(name = "acc_trade_price") val accTradePrice: Double?, // 누적 거래대금 (UTC 0시 기준)
    @Json(name = "acc_trade_price_24h") val accTradePrice24h: Double?, // 24시간 누적 거래대금
    @Json(name = "trade_time") val tradeTime: String?, // 최근 거래 시각(UTC) - HHmmss
    @Json(name = "trade_timestamp") val tradeTimestamp: Long?, // 최근 거래 타임스탬프
    @Json(name = "trade_date") val tradeDate: String?, // 최근 거래 일자(UTC) - yyyyMMdd
    @Json(name = "ask_bid") val askBid: String?, // 매수/매도 구분, ASK : 매도 BID : 매수
    @Json(name = "acc_ask_volume") val accAskVolume: Double?, // 누적 매도량
    @Json(name = "acc_bid_volume") val accBidVolume: Double?, // 누적 매수량
    @Json(name = "highest_52_week_price") val highest52WeekPrice: Double?, // 52주 최고가
    @Json(name = "highest_52_week_date") val highest52WeekDate: String?, // 52주 최고가 달성일 yyyy-MM-dd
    @Json(name = "lowest_52_week_price") val lowest52WeekPrice: Double?, // 52주 최저가
    @Json(name = "lowest_52_week_date") val lowest52WeekDate: String?, // 52주 최저가 달성일 yyyy-MM-dd
    @Json(name = "timestamp") val timestamp: Long?, // 타임스탬프
    @Json(name = "market_warning") val marketWarning: String?, // 유의 종목 여부 - NONE : 해당 사항 없음, CAUTION : 투자유의
    @Json(name = "delisting_date") val delistingDate: String?, // 상장폐지 예정일 yyyy-MM-dd
    @Json(name = "is_trading_suspended") val isTradingSuspended: Boolean?, // 거래 정지 여부
)