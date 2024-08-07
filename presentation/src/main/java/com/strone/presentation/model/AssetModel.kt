package com.strone.presentation.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class AssetModel(
    val id: String,    // 유저 id
    val krw: String,  // 보유 KRW
    val totalBuyKrw: String,    // 총 매수 KRW
    val holdings: List<HoldingCryptoModel> // 보유 중인 코인
) : Parcelable{

    @Parcelize
    @Immutable
    data class HoldingCryptoModel(
        val code: String,    // 코드 ex) KRW-BTC
        val price: String,   // 체결 가격
        val volume: String,  // 보유량
    ) : Parcelable
}