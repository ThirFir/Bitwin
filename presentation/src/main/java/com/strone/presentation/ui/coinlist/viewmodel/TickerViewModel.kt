package com.strone.presentation.ui.coinlist.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.strone.domain.model.Market
import com.strone.domain.model.Ticker
import com.strone.domain.usecase.FetchMarketUseCase
import com.strone.domain.usecase.FetchTickerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TickerViewModel @Inject constructor(
    private val fetchMarketUseCase: FetchMarketUseCase,
    private val fetchTickerUseCase: FetchTickerUseCase
) : ViewModel() {

    private val _tickers: MutableStateFlow<MutableMap<String, MutableSharedFlow<Ticker>>> = MutableStateFlow(mutableMapOf())

    val tickers: StateFlow<Map<String, SharedFlow<Ticker>>> = _tickers

    private val _markets: MutableStateFlow<List<Market>> = MutableStateFlow(emptyList())
    val markets: StateFlow<List<Market>> = _markets

    init {
        fetchMarkets()
        viewModelScope.run {
            launch {
                markets.collect {
                    onMarketsResponse(it)
                }
            }
            launch {
                tickers.collect { map ->
                    onTickersResponse(map.values.toList())
                }
            }
        }
    }

    private fun fetchMarkets() {
        viewModelScope.launch {
            fetchMarketUseCase().onSuccess { markets ->
                _markets.emit(markets)
            }.onFailure {
                // TODO : 코인 목록을 가져오는데 실패했을 때 처리
            }
        }
    }

    private suspend fun fetchTicker(markets: List<Market>) {
        fetchTickerUseCase(markets).onSuccess {
            it.collect { ticker ->
                _tickers.value[ticker.code]?.emit(ticker)
            }
        }.onFailure {
            // TODO : 실시간 시세를 가져오는데 실패했을 때 처리
        }
    }

    private suspend fun onTickersResponse(tickers: List<Flow<Ticker>>) {
        tickers.forEach {
            viewModelScope.launch {
                it.collect {
                    // TODO : Each ticker
                }
            }
        }
    }

    private suspend fun onMarketsResponse(markets: List<Market>) {
        val marketMap = mutableMapOf<String, MutableSharedFlow<Ticker>>()
        markets.forEach { m ->
            marketMap[m.code] = MutableSharedFlow(
                replay = 1,
                extraBufferCapacity = 0,
                onBufferOverflow = BufferOverflow.DROP_OLDEST
            )
        }
        _tickers.emit(marketMap)
        fetchTicker(markets)
    }
}
