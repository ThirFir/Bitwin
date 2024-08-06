package com.strone.domain.usecase

import com.strone.domain.repository.OrderbookRepository
import javax.inject.Inject

class StopFetchingOrderbookUseCase @Inject constructor(
    private val orderbookRepository: OrderbookRepository
) {

    operator fun invoke() {
        orderbookRepository.closeWebSocket()
    }
}