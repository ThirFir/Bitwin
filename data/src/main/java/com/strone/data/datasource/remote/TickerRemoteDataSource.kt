package com.strone.data.datasource.remote

import com.strone.data.api.websocket.TickerWebSocketListener
import com.strone.data.response.websocket.TickerResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.WebSocket
import javax.inject.Inject

class TickerRemoteDataSource @Inject constructor(
    private val webSocket: WebSocket,
    private val tickerWebSocketListener: TickerWebSocketListener
) {

    fun getTickerResponse(json: String) : Flow<TickerResponse> {
        webSocket.send(json)
        return tickerWebSocketListener.getResponse()
    }
}