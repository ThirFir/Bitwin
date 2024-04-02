package com.strone.data.api.websocket

import com.squareup.moshi.Moshi
import com.strone.data.response.websocket.TickerResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import javax.inject.Inject

class TickerWebSocketListener @Inject constructor(
    private val moshi: Moshi
) : WebSocketListener() {

    private val tickerResponseFlowable = MutableStateFlow(null as TickerResponse?)

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        super.onMessage(webSocket, bytes)
        val tickerResp = moshi.adapter(TickerResponse::class.java).fromJson(bytes.utf8())
        tickerResp?.let { onReceiveTickerResponse(it) }
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
    }

    private fun onReceiveTickerResponse(tickerResponse: TickerResponse) {
        tickerResponseFlowable.tryEmit(tickerResponse)
    }

    fun getTickerResponse(): Flow<TickerResponse?> {
        return tickerResponseFlowable
    }
}