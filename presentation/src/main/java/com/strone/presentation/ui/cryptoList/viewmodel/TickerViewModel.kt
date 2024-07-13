package com.strone.presentation.ui.cryptoList.viewmodel

import androidx.lifecycle.viewModelScope
import com.strone.core.CryptoNamespace
import com.strone.core.viewmodel.CryptoBaseViewModel
import com.strone.domain.model.Market
import com.strone.domain.model.Ticker
import com.strone.domain.usecase.FetchTickerUseCase
import com.strone.presentation.CryptoSortState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TickerViewModel @Inject constructor(
    private val fetchTickerUseCase: FetchTickerUseCase,
) : CryptoBaseViewModel() {

    private val _tickers: MutableStateFlow<Map<String, MutableStateFlow<Ticker>>> = MutableStateFlow(
        linkedMapOf()
    )
    val tickers: StateFlow<Map<String, StateFlow<Ticker>>>
        get() = _tickers

    init {
        viewModelScope.launch {
            CryptoNamespace.isFetched.collect { isFetched ->
                if (isFetched) {
                    fetchTicker(CryptoNamespace.markets.values.toList())
                }
            }
        }
    }

    private suspend fun fetchTicker(markets: List<Market>) {
        fetchTickerUseCase.fetchTickerSnapshot(markets).onSuccess { tickers ->

            val initialMap = linkedMapOf<String, MutableStateFlow<Ticker>>()
            tickers.forEach { ticker ->
                initialMap[ticker.code] = MutableStateFlow(ticker)
            }
            _tickers.emit(initialMap)

            fetchTickerUseCase.fetchTickerStreaming(markets).onSuccess { streamingTickerFlow ->
                streamingTickerFlow.collect { ticker ->
                    _tickers.value[ticker.code]?.emit(ticker)
                }
            }.onFailure {
                // TODO : Streaming 시세 Failure
            }
        }.onFailure {
            // TODO : 초기 시세 Failure
        }
    }

    fun sortTickers(state: CryptoSortState) {
        val sortedTickers = when (state) {
            CryptoSortState.NAME_DESCENDING -> _tickers.value.toList().sortedByDescending { CryptoNamespace.markets[it.second.value.code]?.koreanName }
            CryptoSortState.NAME_ASCENDING -> _tickers.value.toList().sortedBy { CryptoNamespace.markets[it.second.value.code]?.koreanName }
            CryptoSortState.PRICE_DESCENDING -> _tickers.value.toList().sortedByDescending { it.second.value.tradePrice }
            CryptoSortState.PRICE_ASCENDING -> _tickers.value.toList().sortedBy { it.second.value.tradePrice }
            CryptoSortState.CHANGE_RATE_DESCENDING -> _tickers.value.toList().sortedByDescending { it.second.value.changeRate }
            CryptoSortState.CHANGE_RATE_ASCENDING -> _tickers.value.toList().sortedBy { it.second.value.changeRate }
            CryptoSortState.VOLUME_DESCENDING -> _tickers.value.toList().sortedByDescending { it.second.value.accTradePrice24h }
            CryptoSortState.VOLUME_ASCENDING -> _tickers.value.toList().sortedBy { it.second.value.accTradePrice24h }
        }

        if(sortedTickers.isEmpty()) return

        val sortedMap = mutableMapOf<String, MutableStateFlow<Ticker>>()
        sortedTickers.forEach { (key, value) ->
            sortedMap[key] = MutableStateFlow(value.value)
        }

        _tickers.value = sortedMap
    }
}
