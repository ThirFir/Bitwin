package com.strone.data.api.websocket

import com.squareup.moshi.Moshi
import com.strone.data.response.websocket.ErrorStreamingResponse
import com.strone.data.response.websocket.UpbitStreamingResponse
import kotlinx.coroutines.channels.SendChannel
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

abstract class UpbitWebSocketListener(
    protected val moshi: Moshi,
) : WebSocketListener() {

    lateinit var channel: SendChannel<UpbitStreamingResponse>

    override fun onOpen(webSocket: WebSocket, response: Response) {
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        val resp = parseResponse(bytes)
        resp?.let {
            onReceiveResponse(it)
        }
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        channel.trySend(ErrorStreamingResponse(t))
    }

    protected abstract fun parseResponse(bytes: ByteString): UpbitStreamingResponse?

    private fun onReceiveResponse(response: UpbitStreamingResponse) {
        channel.trySend(response)
    }

    fun closeChannel() {
        channel.close()
    }
}
