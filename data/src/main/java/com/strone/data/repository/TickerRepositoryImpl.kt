package com.strone.data.repository

import com.strone.data.datasource.remote.TickerRemoteDataSource
import com.strone.data.mapper.toTicker
import com.strone.data.request.RequestType
import com.strone.data.response.rest.TickerSnapshotResponse
import com.strone.data.response.websocket.TickerStreamingResponse
import com.strone.data.util.getSendJson
import com.strone.domain.model.Ticker
import com.strone.domain.repository.TickerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TickerRepositoryImpl @Inject constructor(
    private val tickerRemoteDataSource: TickerRemoteDataSource
) : TickerRepository {

    private var isStreaming = false

    override suspend fun fetchTickerSnapshotResponse(codes: List<String>): List<Ticker> {
        val query = codes.joinToString(",")
        return tickerRemoteDataSource.fetchTickerSnapshotResponse(query).map(TickerSnapshotResponse::toTicker)
    }

    override suspend fun fetchStreamingResponse(codes: List<String>): Flow<Ticker> {
        val json = codes.getSendJson(RequestType.TICKER)

        return if (isStreaming)
            tickerRemoteDataSource.fetchStreamingResponse()
                .map(TickerStreamingResponse::toTicker)
        else
            tickerRemoteDataSource.fetchStreamingResponse(json)
                .map(TickerStreamingResponse::toTicker).also {
                    isStreaming = true
                }
    }
}