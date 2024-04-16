package com.strone.domain.model

object CryptoMarkets {
    private val name: MutableMap<String, Market> = mutableMapOf()

    fun putMarkets(markets: List<Market>) {
        markets.forEach {
            name[it.code] = it
        }
    }

    fun marketOf(code: String): Market =
        name[code] ?: throw IllegalArgumentException("Unknown market code: $code")
}