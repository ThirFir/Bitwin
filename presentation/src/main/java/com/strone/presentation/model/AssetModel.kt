package com.strone.presentation.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
@Immutable
data class AssetModel(
    val id: String,    // 유저 id
    val krw: Long,  // 보유 KRW
    val totalBuyKrw: Long,    // 총 매수 KRW
    val holdings: List<HoldingCryptoModel> // 보유 중인 코인
) : Parcelable{

    @Parcelize
    @Immutable
    data class HoldingCryptoModel(
        val code: String,    // 코드 ex) KRW-BTC
        val price: BigDecimal,   // 체결 가격
        val volume: BigDecimal,  // 보유량
    ) : Parcelable
}