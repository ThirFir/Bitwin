package com.strone.data.datasource.remote

import com.strone.core.qualifier.WebSocket
import com.strone.data.api.websocket.TickerWebSocketListener
import com.strone.data.response.websocket.TickerResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.Request
import javax.inject.Inject

class TickerRemoteDataSource @Inject constructor(
    @WebSocket private val client: okhttp3.OkHttpClient,
    private val request: Request,
    private val tickerWebSocketListener: TickerWebSocketListener
) {

    fun getTickerResponse(json: String) : Flow<TickerResponse> {
        initWebSocket(json)
        return tickerWebSocketListener.getResponse()
    }

    private fun initWebSocket(json: String) {
        val webSocket = client.newWebSocket(request, tickerWebSocketListener)
        client.dispatcher().executorService().shutdown()
        webSocket.send(json)
    }
}