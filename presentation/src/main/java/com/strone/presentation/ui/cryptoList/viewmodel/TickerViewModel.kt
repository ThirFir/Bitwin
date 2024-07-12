package com.strone.presentation.ui.cryptoList.viewmodel

import android.util.Log
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

    private val _tickers: MutableStateFlow<Map<String, Ticker>> = MutableStateFlow(emptyMap())
    val tickers: StateFlow<Map<String, Ticker>>
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
        fetchTickerUseCase(markets).onSuccess {
            it.collect { ticker ->
                val map = _tickers.value.toMutableMap()
                map[ticker.code] = ticker
                _tickers.emit(map)
            }
        }.onFailure {
            // TODO : 실시간 시세를 가져오는데 실패했을 때 처리
        }
    }

    suspend fun sortTickers(state: CryptoSortState) {
        val sortedTickers = when (state) {
            CryptoSortState.NAME_DESCENDING -> _tickers.value.toList().sortedByDescending { it.second.signature }
            CryptoSortState.NAME_ASCENDING -> _tickers.value.toList().sortedBy { it.second.signature }
            CryptoSortState.PRICE_DESCENDING -> _tickers.value.toList().sortedByDescending { it.second.tradePrice }
            CryptoSortState.PRICE_ASCENDING -> _tickers.value.toList().sortedBy { it.second.tradePrice }
            CryptoSortState.CHANGE_RATE_DESCENDING -> _tickers.value.toList().sortedByDescending { it.second.changeRate }
            CryptoSortState.CHANGE_RATE_ASCENDING -> _tickers.value.toList().sortedBy { it.second.changeRate }
            CryptoSortState.VOLUME_DESCENDING -> _tickers.value.toList().sortedByDescending { it.second.accTradeVolume24h }
            CryptoSortState.VOLUME_ASCENDING -> _tickers.value.toList().sortedBy { it.second.accTradeVolume24h }
        }
        _tickers.emit(sortedTickers.toMap())
    }
}
