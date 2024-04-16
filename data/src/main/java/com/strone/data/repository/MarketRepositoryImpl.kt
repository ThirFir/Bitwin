package com.strone.data.repository

import com.strone.data.datasource.remote.MarketRemoteDataSource
import com.strone.data.mapper.toMarket
import com.strone.domain.model.Market
import com.strone.domain.repository.MarketRepository
import javax.inject.Inject

class MarketRepositoryImpl @Inject constructor(
    private val marketRemoteDataSource: MarketRemoteDataSource
) : MarketRepository {

    override suspend fun getAllMarkets() : List<Market> {
        return marketRemoteDataSource.getAllMarkets().map { it.toMarket() }
    }
}