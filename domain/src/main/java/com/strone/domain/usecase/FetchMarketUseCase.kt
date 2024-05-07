package com.strone.domain.usecase

import com.strone.domain.model.CryptoMarkets
import com.strone.domain.model.Market
import com.strone.domain.repository.MarketRepository
import javax.inject.Inject

class FetchMarketUseCase @Inject constructor(
    private val marketRepository: MarketRepository
) {

    suspend operator fun invoke() : Result<List<Market>> {
        return try {
            val markets = marketRepository.fetchAllMarkets()
            CryptoMarkets.putMarkets(markets)
            Result.success(markets)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}