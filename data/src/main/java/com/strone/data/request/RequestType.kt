package com.strone.data.request

import com.strone.domain.constants.CryptoConstants

enum class RequestType(
    val value: String
) {
    TICKER(CryptoConstants.TICKER),
    ORDERBOOK(CryptoConstants.ORDERBOOK),
    TRADE(CryptoConstants.TRADE)
}