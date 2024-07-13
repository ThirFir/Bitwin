package com.strone.domain.usecase

import com.strone.domain.model.Market
import com.strone.domain.model.MarketType
import com.strone.domain.model.Ticker
import com.strone.domain.repository.TickerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

class FetchTickerUseCase @Inject constructor(
    private val tickerRepository: TickerRepository
) {

    suspend fun fetchTickerSnapshot(markets: List<Market>) : Result<List<Ticker>> {
        return try {
            Result.success(tickerRepository.fetchTickerSnapshotResponse(markets.map(Market::code)).filter {
                it.type == MarketType.KRW
            })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun fetchTickerStreaming(markets: List<Market>) : Result<Flow<Ticker>> {
        return try {
            Result.success(tickerRepository.fetchTickerStreamingResponse(markets.map(Market::code)).filter {
                it.type == MarketType.KRW
            })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
