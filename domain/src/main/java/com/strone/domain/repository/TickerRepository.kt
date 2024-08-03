package com.strone.domain.repository

import com.strone.domain.model.Ticker

interface TickerRepository : WebSocketRepository<Ticker> {
    suspend fun fetchTickerSnapshotResponse(codes: List<String>): List<Ticker>
}