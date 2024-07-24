package com.strone.data.api.websocket

import com.squareup.moshi.Moshi
import com.strone.data.response.websocket.OrderbookResponse
import okio.ByteString
import javax.inject.Inject

class OrderbookWebSocketListener @Inject constructor(
    moshi: Moshi
) : UpbitWebSocketListener<OrderbookResponse>(moshi) {

    override fun parseResponse(bytes: ByteString): OrderbookResponse? {
        return moshi.adapter(OrderbookResponse::class.java).fromJson(bytes.utf8())
    }
}