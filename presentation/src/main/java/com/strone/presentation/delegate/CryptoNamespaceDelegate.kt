package com.strone.presentation.delegate

import com.strone.domain.qualifier.ApplicationScope
import com.strone.domain.usecase.FetchMarketUseCase
import com.strone.presentation.mapper.toMarketModel
import com.strone.presentation.model.MarketModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

interface CryptoNamespaceDelegate {
    val markets: StateFlow<Map<String, MarketModel>>
}

class CryptoNamespaceDelegateImpl @Inject constructor(
    @ApplicationScope coroutineScope: CoroutineScope,
    fetchMarketUseCase: FetchMarketUseCase,
) : CryptoNamespaceDelegate {
    override val markets: StateFlow<Map<String, MarketModel>> =
        fetchMarketUseCase(Unit).map { result ->
            result.getOrThrow().mapValues { it.value.toMarketModel() }
        }.stateIn(scope = coroutineScope, started = SharingStarted.Eagerly, initialValue = mutableMapOf())
}