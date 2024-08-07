package com.strone.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class AssetEntity(
    @field:Json(name = "id") @PrimaryKey val id: String,    // 유저 id
    @field:Json(name = "krw") val krw: String,  // 보유 KRW
    @field:Json(name = "total_buy_krw") val totalBuyKrw: String,    // 총 매수 KRW
    @field:Json(name = "holding_crypto") val holdings: List<HoldingCryptoEntity> // 보유 중인 코인
    // 총 보유자산 = 보유 KRW + holding_crypto.sum(보유량 * 현재가)
    // 총 매수 = holding_crypto.sum(보유량 * 체결 가격)
)

@JsonClass(generateAdapter = true)
data class HoldingCryptoEntity(
    @field:Json(name = "code") val code: String,    // 코드 ex) KRW-BTC
    @field:Json(name = "price") val price: String,   // 체결 가격
    @field:Json(name = "volume") val volume: String, // 보유량
    // 총 매수 = 보유량 * 체결 가격
)