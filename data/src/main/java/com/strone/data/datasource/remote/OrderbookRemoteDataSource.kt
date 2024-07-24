package com.strone.data.datasource.remote

import com.strone.core.qualifier.WebSocket
import com.strone.data.api.websocket.OrderbookWebSocketListener
import com.strone.data.response.websocket.OrderbookResponse
import okhttp3.Request
import javax.inject.Inject

class OrderbookRemoteDataSource @Inject constructor(
    @WebSocket client: okhttp3.OkHttpClient,
    request: Request,
    orderbookWebSocketListener: OrderbookWebSocketListener
) : WebSocketRemoteDataSource<OrderbookResponse>(client, request, orderbookWebSocketListener) {

}