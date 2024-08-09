package com.strone.domain.model

import java.math.BigDecimal

data class Asset(
    val id: String,    // 유저 id
    val krw: Long,  // 보유 KRW
    val totalBuyKrw: Long,    // 총 매수 KRW
    val holdings: List<HoldingCrypto> // 보유 중인 코인
) {

    data class HoldingCrypto(
        val code: String,    // 코드 ex) KRW-BTC
        val price: BigDecimal,   // 체결 가격
        val volume: BigDecimal,  // 보유량
    )
}