package com.strone.presentation.ui.cryptoList.viewmodel

import androidx.lifecycle.viewModelScope
import com.strone.core.CryptoNamespace
import com.strone.core.viewmodel.CryptoBaseViewModel
import com.strone.domain.model.Market
import com.strone.domain.model.Ticker
import com.strone.domain.usecase.FetchTickerUseCase
import com.strone.presentation.mapper.toTickerModel
import com.strone.presentation.model.TickerModel
import com.strone.presentation.state.CryptoSortState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TickerViewModel @Inject constructor(
    private val fetchTickerUseCase: FetchTickerUseCase,
) : CryptoBaseViewModel() {

    private val _tickers: MutableStateFlow<Map<String, MutableStateFlow<TickerModel>>> = MutableStateFlow(
        linkedMapOf()
    )
    val tickers: StateFlow<Map<String, StateFlow<TickerModel>>>
        get() = _tickers

    private val _hotTickers: MutableStateFlow<List<TickerModel>> = MutableStateFlow(listOf())
    val hotTickers: StateFlow<List<TickerModel>>
        get() = _hotTickers

    init {
        viewModelScope.launchWithUiState {
            CryptoNamespace.isFetched.collect { isFetched ->
                if (isFetched) {
                    fetchTicker(CryptoNamespace.markets.values.toList())
                }
            }
        }
    }

    private suspend fun fetchTicker(markets: List<Market>) {
        fetchTickerUseCase.fetchTickerSnapshot(markets)
            .emitUiState()
            .onSuccess { tickers ->
                val tickerModels = tickers.map(Ticker::toTickerModel)
                emitTickers(tickerModels)
                emitHotTickers(tickerModels)

                fetchTickerUseCase.fetchTickerStreaming(markets).onSuccess { streamingTickerFlow ->
                    streamingTickerFlow.collect { ticker ->
                        val tickerModel = ticker.toTickerModel()
                        _tickers.value[ticker.code]?.emit(tickerModel)
                        _hotTickers.emit(_hotTickers.value.map { original ->
                            if (original.code == ticker.code)
                                tickerModel
                            else
                                original
                        })
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
            CryptoSortState.CHANGE_RATE_DESCENDING -> _tickers.value.toList().sortedByDescending { it.second.value.signedChangeRate }
            CryptoSortState.CHANGE_RATE_ASCENDING -> _tickers.value.toList().sortedBy { it.second.value.signedChangeRate }
            CryptoSortState.VOLUME_DESCENDING -> _tickers.value.toList().sortedByDescending { it.second.value.accTradePrice24h }
            CryptoSortState.VOLUME_ASCENDING -> _tickers.value.toList().sortedBy { it.second.value.accTradePrice24h }
        }

        if(sortedTickers.isEmpty()) return

        val sortedMap = mutableMapOf<String, MutableStateFlow<TickerModel>>()
        sortedTickers.forEach { (key, value) ->
            sortedMap[key] = MutableStateFlow(value.value)
        }

        _tickers.value = sortedMap
    }

    private suspend fun emitHotTickers(tickers: List<TickerModel>) {
        _hotTickers.emit(
            tickers.sortedByDescending { it.accTradePrice }.take(4)
        )
    }

    private suspend fun emitTickers(tickers: List<TickerModel>) {
        val initialMap = linkedMapOf<String, MutableStateFlow<TickerModel>>()
        tickers.forEach { ticker ->
            initialMap[ticker.code] = MutableStateFlow(ticker)
        }
        _tickers.emit(initialMap)
    }
}