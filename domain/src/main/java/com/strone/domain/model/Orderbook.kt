package com.strone.domain.model

import com.strone.domain.model.type.OrderType
import java.math.BigDecimal

data class Orderbook(
    val code: String, // 마켓 코드 ex) KRW-BTC
    val totalAskSize: BigDecimal, // 호가 매도 총 잔량
    val totalBidSize: BigDecimal, // 호가 매수 총 잔량
    val orderbookUnits: List<OrderbookUnit>, // 호가 정보
    val timestamp: Long, // 타임스탬프
) : StreamingModel() {

    data class OrderbookUnit(
        val price: BigDecimal,
        val size: BigDecimal,
        val orderType: OrderType
    )
}
