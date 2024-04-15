package com.strone.presentation.ui.coinlist.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.strone.domain.model.Ticker
import com.strone.domain.usecase.GetMarketUseCase
import com.strone.domain.usecase.GetTickerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TickerViewModel @Inject constructor(
    private val getMarketUseCase: GetMarketUseCase,
    private val getTickerUseCase: GetTickerUseCase
) : ViewModel() {

    private val _tickers = MutableStateFlow<MutableMap<String, Ticker>>(mutableMapOf())
    val tickers: StateFlow<Map<String, Ticker>> get() = _tickers

    fun getTicker() {
        viewModelScope.launch {
            getMarketUseCase().onSuccess { market ->
                getTickerUseCase(market).onSuccess {
                    it.collect { ticker ->
                        _tickers.value[ticker.code] = ticker
                    }
                }.onFailure {

                }
            }.onFailure {

            }
        }
    }
}