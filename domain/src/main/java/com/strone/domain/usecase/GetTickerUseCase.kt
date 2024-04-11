package com.strone.domain.usecase

import com.strone.domain.model.Ticker
import com.strone.domain.repository.TickerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTickerUseCase @Inject constructor(
    private val tickerRepository: TickerRepository
) {

    suspend operator fun invoke() : Result<Flow<Ticker>> {
        return try {
            Result.success(tickerRepository.getTickerResponse())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
