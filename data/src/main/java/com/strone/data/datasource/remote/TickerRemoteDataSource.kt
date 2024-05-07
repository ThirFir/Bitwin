package com.strone.data.datasource.remote

import com.strone.core.qualifier.WebSocket
import com.strone.data.api.rest.TickerApi
import com.strone.data.api.websocket.TickerWebSocketListener
import com.strone.data.response.rest.TickerSnapshotResponse
import com.strone.data.response.websocket.TickerStreamingResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.Request
import javax.inject.Inject

class TickerRemoteDataSource @Inject constructor(
    @WebSocket private val client: okhttp3.OkHttpClient,
    private val request: Request,
    private val tickerWebSocketListener: TickerWebSocketListener,
    private val tickerApi: TickerApi
) {

    fun fetchTickerStreamingResponse(json: String) : Flow<TickerStreamingResponse> {
        initWebSocket(json)
        return tickerWebSocketListener.fetchResponse()
    }

    suspend fun fetchTickerSnapshotResponse(query: String): List<TickerSnapshotResponse> {
        return tickerApi.fetchAllTickers(query)
    }

    private fun initWebSocket(json: String) {
        val webSocket = client.newWebSocket(request, tickerWebSocketListener)
        client.dispatcher().executorService().shutdown()
        webSocket.send(json)
    }
}