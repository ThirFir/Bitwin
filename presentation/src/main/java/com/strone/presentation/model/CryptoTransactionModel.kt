package com.strone.presentation.model

import androidx.compose.runtime.Stable
import com.strone.domain.model.CryptoTransaction
import java.math.BigDecimal

@Stable
data class CryptoTransactionModel(
    val code: String,
    var price: BigDecimal,
    var volume: BigDecimal,
    var totalPrice: Long,
) {
    fun toCryptoTransaction() = CryptoTransaction(code, price, volume, totalPrice)
}