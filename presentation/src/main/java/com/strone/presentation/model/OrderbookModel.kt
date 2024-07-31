package com.strone.presentation.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.strone.domain.model.type.OrderType
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
class OrderbookModel(
    val code: String, // 마켓 코드 ex) KRW-BTC
    val totalAskSize: Double, // 호가 매도 총 잔량
    val totalBidSize: Double, // 호가 매수 총 잔량
    val orderbookUnitModels: List<OrderbookUnitModel>, // 호가 정보
    val timestamp: Long, // 타임스탬프
): Parcelable {

    @Parcelize
    @Immutable
    data class OrderbookUnitModel(
        val price: Double,
        val size: Double,
        val orderType: OrderType
    ): Parcelable
}
