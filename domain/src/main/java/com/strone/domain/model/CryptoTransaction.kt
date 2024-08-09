package com.strone.domain.model

import java.math.BigDecimal

data class CryptoTransaction(
    val code: String,
    val price: BigDecimal,
    val volume: BigDecimal,
    val totalPrice: Long,
)

