package com.strone.domain.model

data class Ticker(
    val type: String,   // 타입, ticker : 현재가
    val code: String,   // 마켓 코드 ex) KRW-BTC   웹소켓 전용
    val market: String, // code와 같음, REST API 전용
    val openingPrice: Double,  // 시가
    val highPrice: Double,        // 고가
    val lowPrice: Double,      // 저가
    val tradePrice: Double,  // 현재가
    val prevClosingPrice: Double, // 전일 종가
    val change: String, // 전일 대비 RISE : 상승 EVEN : 보합 FALL : 하락
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
    val askBid: String, // 매수/매도 구분, ASK : 매도 BID : 매수
    val accAskVolume: Double, // 누적 매도량
    val accBidVolume: Double, // 누적 매수량
    val highest52WeekPrice: Double, // 52주 최고가
    val highest52WeekDate: String, // 52주 최고가 달성일 yyyy-MM-dd
    val lowest52WeekPrice: Double, // 52주 최저가
    val lowest52WeekDate: String, // 52주 최저가 달성일 yyyy-MM-dd
    val timestamp: Long, // 타임스탬프
    val marketWarning: String, // 유의 종목 여부 - NONE : 해당 사항 없음, CAUTION : 투자유의
    val delistingDate: String, // 상장폐지 예정일 yyyy-MM-dd
    val isTradingSuspended: Boolean, // 거래 정지 여부
)
