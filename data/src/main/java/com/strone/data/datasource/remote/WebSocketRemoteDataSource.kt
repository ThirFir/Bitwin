package com.strone.data.datasource.remote

import com.strone.core.qualifier.WebSocket
import com.strone.data.api.websocket.UpbitWebSocketListener
import com.strone.data.response.websocket.UpbitWebSocketResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.Request

abstract class WebSocketRemoteDataSource<T : UpbitWebSocketResponse>(
    @WebSocket private val client: okhttp3.OkHttpClient,
    private val request: Request,
    protected val webSocketListener: UpbitWebSocketListener<T>
) {

    private lateinit var webSocket: okhttp3.WebSocket

    private fun initWebSocket(json: String) {
        webSocket = client.newWebSocket(request, webSocketListener)
        webSocket.send(json)
    }

    fun fetchStreamingResponse(json: String) : Flow<T> {
        initWebSocket(json)
        return webSocketListener.fetchResponse()
    }

    fun closeWebSocket(reason: String = "TODO") {
        webSocket.close(1000, reason)
    }
}