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
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class TickerRepositoryImpl @Inject constructor(
    private val tickerRemoteDataSource: TickerRemoteDataSource
) : TickerRepository {

    override suspend fun fetchTickerResponse(codes: List<String>): Flow<Ticker> {
        val query = codes.joinToString(",")

        val initialDataFlow = tickerRemoteDataSource.fetchTickerSnapshotResponse(query)
            .asFlow()
            .map(TickerSnapshotResponse::toTicker)

        val streamingDataFlow = flow {
            val json = codes.getSendJson()
            tickerRemoteDataSource.fetchTickerStreamingResponse(json)
                .collect { response ->
                    emit(response.toTicker())
                }
        }

        return initialDataFlow.onCompletion {
            emitAll(streamingDataFlow)
        }
    }
}