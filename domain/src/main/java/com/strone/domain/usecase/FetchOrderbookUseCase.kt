package com.strone.domain.usecase

import com.strone.domain.exception.ExceptionHandler
import com.strone.domain.exception.mapResultWith
import com.strone.domain.model.Orderbook
import com.strone.domain.repository.OrderbookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchOrderbookUseCase @Inject constructor(
    private val orderbookRepository: OrderbookRepository,
    private val exceptionHandler: ExceptionHandler
) {

    suspend fun fetchOrderbookStreaming(code: String) : Flow<Result<Orderbook>> {
        return orderbookRepository.fetchStreamingResponse(code)
            .mapResultWith(exceptionHandler)
    }
}