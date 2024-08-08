package com.strone.domain.usecase

import com.strone.domain.exception.ExceptionHandler
import com.strone.domain.model.Market
import com.strone.domain.qualifier.IoDispatcher
import com.strone.domain.repository.MarketRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class FetchMarketUseCase @Inject constructor(
    private val marketRepository: MarketRepository,
    exceptionHandler: ExceptionHandler,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : FlowResultUseCase<Unit, Map<String, Market>>(exceptionHandler, coroutineDispatcher) {

    override fun execute(params: Unit) : Flow<Map<String, Market>> {
        return marketRepository.fetchAllMarkets().transform { markets ->
            emit(markets.associateBy { it.code })
        }
    }
}