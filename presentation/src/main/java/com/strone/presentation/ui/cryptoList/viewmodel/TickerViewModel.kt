package com.strone.presentation.ui.cryptoList.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.strone.core.CryptoNamespace
import com.strone.core.viewmodel.BaseViewModel
import com.strone.domain.model.Market
import com.strone.domain.model.Ticker
import com.strone.domain.usecase.FetchTickerUseCase
import com.strone.presentation.delegate.UserDelegate
import com.strone.presentation.mapper.toTickerModel
import com.strone.presentation.model.TickerModel
import com.strone.presentation.state.CryptoSortState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TickerViewModel @Inject constructor(
    private val fetchTickerUseCase: FetchTickerUseCase,
    userDelegate: UserDelegate
) : BaseViewModel(), UserDelegate by userDelegate {

    private val _tickers = mutableStateListOf<TickerModel>()
    val tickers get() = _tickers
    private val tickerPositions = mutableMapOf<String, Int>()

    private val _hotTickers = mutableStateListOf<TickerModel>()
    val hotTickers get() = _hotTickers
    private val hotTickerPositions = mutableMapOf<String, Int>()

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
            .onComplete { tickers ->
                val tickerModels = tickers.map(Ticker::toTickerModel)
                emitTickers(tickerModels)
                emitHotTickers(tickerModels)

                fetchTickerUseCase.fetchTickerStreaming(markets)
                    .collect {
                        it.onComplete { ticker ->
                            val tickerModel = ticker.toTickerModel()
                            emitTicker(tickerModel)

                            if (hotTickerPositions.containsKey(tickerModel.code))
                                emitHotTicker(tickerModel)
                        }
                    }
            }
    }

    fun sortTickers(state: CryptoSortState) {
        val sortedTickers = when (state) {
            CryptoSortState.NAME_DESCENDING -> _tickers.sortedByDescending { CryptoNamespace.markets[it.code]?.koreanName }
            CryptoSortState.NAME_ASCENDING -> _tickers.sortedBy { CryptoNamespace.markets[it.code]?.koreanName }
            CryptoSortState.PRICE_DESCENDING -> _tickers.sortedByDescending { it.tradePrice }
            CryptoSortState.PRICE_ASCENDING -> _tickers.sortedBy { it.tradePrice }
            CryptoSortState.CHANGE_RATE_DESCENDING -> _tickers.sortedByDescending { it.signedChangeRate }
            CryptoSortState.CHANGE_RATE_ASCENDING -> _tickers.sortedBy { it.signedChangeRate }
            CryptoSortState.VOLUME_DESCENDING -> _tickers.sortedByDescending { it.accTradePrice24h }
            CryptoSortState.VOLUME_ASCENDING -> _tickers.sortedBy { it.accTradePrice24h }
        }

        emitTickers(sortedTickers)
    }

    private fun emitHotTickers(tickerModels: List<TickerModel>) {
        _hotTickers.clear()
        tickerModels
            .sortedByDescending { it.accTradePrice24h }
            .take(4)
            .forEachIndexed { index, tickerModel ->
                _hotTickers.add(tickerModel)
                hotTickerPositions[tickerModel.code] = index
            }
    }

    private fun emitTickers(tickerModels: List<TickerModel>) {
        _tickers.clear()
        tickerModels.forEachIndexed { index, tickerModel ->
            _tickers.add(tickerModel)
            tickerPositions[tickerModel.code] = index
        }
    }

    private fun emitTicker(tickerModel: TickerModel) {
        _tickers[tickerPositions[tickerModel.code]!!] = tickerModel
    }

    private fun emitHotTicker(tickerModel: TickerModel) {
        _hotTickers[hotTickerPositions[tickerModel.code]!!] = tickerModel
    }
}