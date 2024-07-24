package com.strone.data.request

import com.strone.data.constant.Constant

enum class RequestType(
    val value: String
) {
    TICKER(Constant.TICKER),
    ORDERBOOK(Constant.ORDERBOOK),
    TRADE(Constant.TRADE)
}