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

    private var isStreaming = false

    override suspend fun fetchTickerSnapshotResponse(codes: List<String>): List<Ticker> {
        val query = codes.joinToString(",")
        return tickerRemoteDataSource.fetchTickerSnapshotResponse(query).map(TickerSnapshotResponse::toTicker)
    }

    override suspend fun fetchTickerStreamingResponse(codes: List<String>): Flow<Ticker> {
        val json = codes.getSendJson()

        return if (isStreaming)
            tickerRemoteDataSource.fetchTickerStreamingResponse()
                .map(TickerStreamingResponse::toTicker)
        else
            tickerRemoteDataSource.fetchTickerStreamingResponse(json)
                .map(TickerStreamingResponse::toTicker).also {
                    isStreaming = true
                }
    }
}