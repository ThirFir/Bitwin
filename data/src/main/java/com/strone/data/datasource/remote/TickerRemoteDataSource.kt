package com.strone.data.datasource.remote

import com.strone.core.qualifier.WebSocket
import com.strone.data.api.rest.TickerApi
import com.strone.data.api.websocket.TickerWebSocketListener
import com.strone.data.response.rest.TickerSnapshotResponse
import okhttp3.Request
import javax.inject.Inject

class TickerRemoteDataSource @Inject constructor(
    @WebSocket client: okhttp3.OkHttpClient,
    request: Request,
    tickerWebSocketListener: TickerWebSocketListener,
    private val tickerApi: TickerApi
) : WebSocketRemoteDataSource(client, request, tickerWebSocketListener) {

    suspend fun fetchTickerSnapshotResponse(query: String): List<TickerSnapshotResponse> {
        return tickerApi.fetchAllTickers(query)
    }
}