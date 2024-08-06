package com.strone.data.datasource.remote

import com.strone.core.qualifier.WebSocket
import com.strone.data.api.websocket.UpbitWebSocketListener
import com.strone.data.response.websocket.UpbitStreamingResponse
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import okhttp3.Request

abstract class WebSocketRemoteDataSource(
    @WebSocket private val client: okhttp3.OkHttpClient,
    private val request: Request,
    private val webSocketListener: UpbitWebSocketListener,
) {

    private lateinit var webSocket: okhttp3.WebSocket

    private fun initWebSocket(json: String, channel: SendChannel<UpbitStreamingResponse>) {
        webSocketListener.channel = channel
        webSocket = client.newWebSocket(request, webSocketListener)
        webSocket.send(json)
    }

    fun fetchStreamingResponse(json: String) : Flow<UpbitStreamingResponse> {
        return callbackFlow {
            initWebSocket(json, this)
            awaitClose {
                webSocket.close(1000, "finish")
            }
        }
    }

    fun closeWebSocket() {
        webSocketListener.closeChannel()
    }
}