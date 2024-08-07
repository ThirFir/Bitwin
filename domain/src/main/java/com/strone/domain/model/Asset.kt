package com.strone.domain.model

data class Asset(
    val id: String,    // 유저 id
    val krw: String,  // 보유 KRW
    val totalBuyKrw: String,    // 총 매수 KRW
    val holdings: List<HoldingCrypto> // 보유 중인 코인
) {

    data class HoldingCrypto(
        val code: String,    // 코드 ex) KRW-BTC
        val price: String,   // 체결 가격
        val volume: String,  // 보유량
    )
}