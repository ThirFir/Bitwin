package com.strone.domain.model

data class Orderbook(
    val code: String?, // 마켓 코드 ex) KRW-BTC
    val totalAskSize: Double?, // 호가 매도 총 잔량
    val totalBidSize: Double?, // 호가 매수 총 잔량
    val orderbookUnits: List<OrderbookUnit>?, // 호가 정보
    val timestamp: Long?, // 타임스탬프
) : StreamingModel() {

    data class OrderbookUnit(
        val askPrice: Double?, // 매도 호가
        val bidPrice: Double?, // 매수 호가
        val askSize: Double?, // 매도 잔량
        val bidSize: Double?, // 매수 잔량
    )
}
