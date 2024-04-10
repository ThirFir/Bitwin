package com.strone.data.api.websocket

import com.squareup.moshi.Moshi
import com.strone.data.response.websocket.TickerResponse
import okio.ByteString
import javax.inject.Inject

class TickerWebSocketListener @Inject constructor(
    moshi: Moshi,
) : UpbitWebSocketListener<TickerResponse>(moshi) {

    override fun parseResponse(bytes: ByteString): TickerResponse? {
        return moshi.adapter(TickerResponse::class.java).fromJson(bytes.utf8())
    }
}
