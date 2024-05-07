package com.strone.data.repository

import com.strone.data.datasource.remote.TickerRemoteDataSource
import com.strone.data.mapper.toTicker
import com.strone.data.response.rest.TickerSnapshotResponse
import com.strone.data.response.websocket.TickerStreamingResponse
import com.strone.data.util.getSendJson
import com.strone.domain.model.Ticker
import com.strone.domain.repository.TickerRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TickerRepositoryImpl @Inject constructor(
    private val tickerRemoteDataSource: TickerRemoteDataSource
) : TickerRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun fetchTickerResponse(codes: List<String>): Flow<Ticker> {
        val query = codes.joinToString(",")
        return tickerRemoteDataSource.fetchTickerSnapshotResponse(query)
            .asFlow()
            .map(TickerSnapshotResponse::toTicker)
            .flatMapConcat {
                val json = codes.getSendJson()
                tickerRemoteDataSource.fetchTickerStreamingResponse(json)
                    .map(TickerStreamingResponse::toTicker)
            }
    }
}