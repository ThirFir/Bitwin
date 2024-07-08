package com.strone.core

import com.strone.domain.model.Market
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object CryptoNamespace {
    private val _markets: MutableMap<String, Market> = mutableMapOf()
    val markets: Map<String, Market>
        get() = _markets

    private val _isFetched: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isFetched: StateFlow<Boolean>
        get() = _isFetched

    fun putMarkets(markets: List<Market>) {
        markets.forEach {
            this._markets[it.code] = it
        }
        _isFetched.value = true
    }
}