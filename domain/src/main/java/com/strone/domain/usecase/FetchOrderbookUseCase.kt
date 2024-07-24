package com.strone.domain.usecase

import com.strone.domain.model.Orderbook
import com.strone.domain.repository.OrderbookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchOrderbookUseCase @Inject constructor(
    private val orderbookRepository: OrderbookRepository
) {

    suspend fun fetchOrderbookStreaming(code: String) : Result<Flow<Orderbook>> {
        return try {
            Result.success(orderbookRepository.fetchStreamingResponse(code))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}