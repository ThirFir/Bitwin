package com.strone.domain.model

data class Ticker(
    val code: String,   // 마켓 코드 ex) KRW-BTC   웹소켓 전용
    val signature: String, // 코인 시그니처 ex) BTC, ETH, XRP
    val type: MarketType, // 마켓 구분 코드 KRW(원화), BTC(비트코인), USDT(테더)
    val openingPrice: Double,  // 시가
    val highPrice: Double,     // 고가
    val lowPrice: Double,      // 저가
    val tradePrice: Double,  // 현재가
    val prevClosingPrice: Double, // 전일 종가
    val change: ChangeType, // 전일 대비 RISE : 상승 EVEN : 보합 FALL : 하락
    val changePrice: Double, // 부호 없는 전일 대비 값
    val signedChangePrice: Double, // 전일 대비 값
    val changeRate: Double, // 부호 없는 전일 대비 등락율
    val signedChangeRate: Double, // 전일 대비 등락율
    val tradeVolume: Double, // 가장 최근 거래량
    val accTradeVolume: Double, // 누적 거래량 (UTC 0시 기준)
    val accTradeVolume24h: Double, // 24시간 누적 거래량
    val accTradePrice: Double, // 누적 거래대금 (UTC 0시 기준)
    val accTradePrice24h: Double, // 24시간 누적 거래대금
    val tradeTime: String, // 최근 거래 시각(UTC) - HHmmss
    val tradeTimestamp: Long, // 최근 거래 타임스탬프
    val tradeDate: String, // 최근 거래 일자(UTC) - yyyyMMdd
    val highest52WeekPrice: Double, // 52주 최고가
    val highest52WeekDate: String, // 52주 최고가 달성일 yyyy-MM-dd
    val lowest52WeekPrice: Double, // 52주 최저가
    val lowest52WeekDate: String, // 52주 최저가 달성일 yyyy-MM-dd
    val timestamp: Long, // 타임스탬프
) : StreamingModel()

// delistingDate, isTradingSuspended, askBid, accAskVolume, accBidVolume, type, market, marketWarning
// tradeDateKst, tradeTimeKst, marketWarning