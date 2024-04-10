package com.strone.data.api.websocket

import com.squareup.moshi.Moshi
import com.strone.data.response.websocket.TickerResponse
import com.strone.data.response.websocket.TradeResponse
import com.strone.data.response.websocket.UpbitWebSocketResponse
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.consumeAsFlow
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

abstract class UpbitWebSocketListener<T : UpbitWebSocketResponse>(
    protected val moshi: Moshi,
) : WebSocketListener() {

    private val channel: Channel<T> = Channel(Channel.UNLIMITED)

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        super.onMessage(webSocket, bytes)
        val resp = parseResponse(bytes)
        resp?.let {
            onReceiveResponse(it)
        }
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

    protected abstract fun parseResponse(bytes: ByteString): T?

    private fun onReceiveResponse(response: T) {
        channel.trySend(response)
    }

    fun getResponse() : Flow<T> = channel.consumeAsFlow()
}