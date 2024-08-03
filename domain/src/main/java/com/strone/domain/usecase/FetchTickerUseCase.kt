package com.strone.domain.usecase

import com.strone.domain.exception.ExceptionHandler
import com.strone.domain.exception.mapCatchWith
import com.strone.domain.exception.runCatchWith
import com.strone.domain.model.Market
import com.strone.domain.model.Ticker
import com.strone.domain.model.type.MarketType
import com.strone.domain.repository.TickerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

class FetchTickerUseCase @Inject constructor(
    private val tickerRepository: TickerRepository,
    private val exceptionHandler: ExceptionHandler
) {
    suspend fun fetchTickerSnapshot(markets: List<Market>) : Result<List<Ticker>> {
        return runCatchWith(exceptionHandler) {
            tickerRepository.fetchTickerSnapshotResponse(markets.map(Market::code)).filter {
                it.type == MarketType.KRW
            }
        }
    }

    suspend fun fetchTickerStreaming(markets: List<Market>) : Flow<Result<Ticker>> {
        return tickerRepository.fetchStreamingResponse(markets.map(Market::code)).filter {
            it.type == MarketType.KRW
        }.mapCatchWith(exceptionHandler)
    }

    suspend fun fetchTickerStreaming() : Flow<Result<Ticker>> {
        return fetchTickerStreaming(emptyList())
    }
}
