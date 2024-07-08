package com.strone.domain.usecase

import com.strone.domain.model.Market
import com.strone.domain.model.Ticker
import com.strone.domain.repository.TickerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchTickerUseCase @Inject constructor(
    private val tickerRepository: TickerRepository
) {

    suspend operator fun invoke(markets: List<Market>) : Result<Flow<Ticker>> {
        return try {
            Result.success(tickerRepository.fetchTickerResponse(markets.map(Market::code)))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
