package com.strone.data.repository

import com.strone.data.datasource.remote.MarketRemoteDataSource
import com.strone.data.datasource.remote.TickerRemoteDataSource
import com.strone.data.mapper.toTicker
import com.strone.data.response.websocket.TickerResponse
import com.strone.data.util.getSendJson
import com.strone.domain.model.Ticker
import com.strone.domain.repository.TickerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TickerRepositoryImpl @Inject constructor(
    private val marketRemoteDataSource: MarketRemoteDataSource,
    private val tickerRemoteDataSource: TickerRemoteDataSource
) : TickerRepository {

    override suspend fun getTickerResponse(): Flow<Ticker> {
        val codes = marketRemoteDataSource.getAllMarkets().map {
            it.market
        }
        val json = codes.getSendJson()
        return tickerRemoteDataSource.getTickerResponse(json).map(TickerResponse::toTicker)
    }
}