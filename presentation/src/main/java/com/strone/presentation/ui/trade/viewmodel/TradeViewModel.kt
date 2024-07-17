package com.strone.presentation.ui.trade.viewmodel

import androidx.lifecycle.viewModelScope
import com.strone.core.CryptoNamespace
import com.strone.core.viewmodel.CryptoBaseViewModel
import com.strone.domain.model.Ticker
import com.strone.domain.usecase.FetchTickerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TradeViewModel @Inject constructor(
    private val fetchTickerUseCase: FetchTickerUseCase
) : CryptoBaseViewModel() {

    private val _ticker: MutableStateFlow<Ticker?> = MutableStateFlow(null)
    val ticker: StateFlow<Ticker?>
        get() = _ticker

    suspend fun fetchTicker(code: String) {
        viewModelScope.launch {
            fetchTickerUseCase.fetchTickerStreaming()   // 기존에 열린 Flow 이용
                .onSuccess {
                    it.collect { ticker ->
                        if(ticker.code == code)
                            _ticker.emit(ticker)
                    }
                }.onFailure {

                }
        }
    }

    suspend fun fetchOrderBooks(code: String) {

    }

    suspend fun fetchCandles(code: String) {

    }
}