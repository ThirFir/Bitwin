package com.strone.presentation.ui.trade.viewmodel

import androidx.lifecycle.viewModelScope
import com.strone.presentation.ui.BaseViewModel
import com.strone.domain.usecase.FetchOrderbookUseCase
import com.strone.domain.usecase.FetchTickerUseCase
import com.strone.domain.usecase.StopFetchingOrderbookUseCase
import com.strone.presentation.delegate.CryptoNamespaceDelegate
import com.strone.presentation.delegate.UserDelegate
import com.strone.presentation.mapper.toMarket
import com.strone.presentation.mapper.toOrderbookModel
import com.strone.presentation.mapper.toTickerModel
import com.strone.presentation.model.OrderbookModel
import com.strone.presentation.model.TickerModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel(assistedFactory = TradeViewModel.Factory::class)
class TradeViewModel @AssistedInject constructor(
    @Assisted private val tickerSnapshot: TickerModel,
    private val fetchTickerUseCase: FetchTickerUseCase,
    private val fetchOrderbookUseCase: FetchOrderbookUseCase,
    private val stopFetchingOrderbookUseCase: StopFetchingOrderbookUseCase,
    cryptoNamespaceDelegate: CryptoNamespaceDelegate,
    userDelegate: UserDelegate
) : BaseViewModel(), CryptoNamespaceDelegate by cryptoNamespaceDelegate, UserDelegate by userDelegate {

    private val _ticker: MutableStateFlow<TickerModel> = MutableStateFlow(tickerSnapshot)
    val ticker: StateFlow<TickerModel>
        get() = _ticker

    private val _orderbook: MutableStateFlow<OrderbookModel?> = MutableStateFlow(null)
    val orderbook: StateFlow<OrderbookModel?>
        get() = _orderbook

    suspend fun fetchTicker(code: String) {
        viewModelScope.launchWithUiState {
            fetchTickerUseCase.fetchTickerStreaming(markets.value[code]?.toMarket()!!)
                .collect {
                    it.onComplete { ticker ->
                        if (ticker.code == code)
                            _ticker.emit(ticker.toTickerModel())
                    }
                }
        }
    }

    suspend fun fetchOrderBook(code: String) {
        viewModelScope.launchWithUiState {
            fetchOrderbookUseCase.fetchOrderbookStreaming(code)
                .collect {
                    it.onComplete { orderbook ->
                        _orderbook.emit(orderbook.toOrderbookModel())
                    }
                }
        }
    }

    suspend fun fetchCandles(code: String) {

    }

    private fun stopFetchingOrderbook() {
        stopFetchingOrderbookUseCase()
    }

    override fun onCleared() {
        super.onCleared()
        stopFetchingOrderbook()
    }

    @AssistedFactory
    interface Factory {
        fun create(tickerSnapshot: TickerModel): TradeViewModel
    }
}