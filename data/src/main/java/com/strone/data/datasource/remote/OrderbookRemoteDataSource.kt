package com.strone.data.datasource.remote

import com.strone.domain.qualifier.WebSocket
import com.strone.data.api.websocket.OrderbookWebSocketListener
import okhttp3.Request
import javax.inject.Inject

class OrderbookRemoteDataSource @Inject constructor(
    @WebSocket client: okhttp3.OkHttpClient,
    request: Request,
    orderbookWebSocketListener: OrderbookWebSocketListener
) : WebSocketRemoteDataSource(client, request, orderbookWebSocketListener)