package com.strone.data.repository

import com.strone.data.datasource.remote.MarketRemoteDataSource
import com.strone.data.mapper.asFlow
import com.strone.data.mapper.toMarket
import com.strone.domain.model.Market
import com.strone.domain.repository.MarketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarketRepositoryImpl @Inject constructor(
    private val marketRemoteDataSource: MarketRemoteDataSource
) : MarketRepository {
    override fun fetchAllMarkets(): Flow<List<Market>> {
        return flow {
            emit(marketRemoteDataSource.fetchAllMarkets().map { it.toMarket() })
        }
    }
}