package com.strone.data.api.websocket

import com.squareup.moshi.Moshi
import com.strone.data.response.websocket.TickerStreamingResponse
import okio.ByteString
import javax.inject.Inject

class TickerWebSocketListener @Inject constructor(
    moshi: Moshi,
) : UpbitWebSocketListener(moshi) {

    override fun parseResponse(bytes: ByteString): TickerStreamingResponse? {
        return moshi.adapter(TickerStreamingResponse::class.java).fromJson(bytes.utf8())
    }
}
