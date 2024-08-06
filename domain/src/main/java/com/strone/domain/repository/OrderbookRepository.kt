package com.strone.domain.repository

import com.strone.domain.model.Orderbook

interface OrderbookRepository : WebSocketRepository<Orderbook> {
    fun closeWebSocket()
}