package com.strone.domain.model

object CryptoMarkets {
    val name: MutableMap<String, Market> = mutableMapOf()

    fun putMarkets(markets: List<Market>) {
        markets.forEach {
            name[it.code] = it
        }
    }
}