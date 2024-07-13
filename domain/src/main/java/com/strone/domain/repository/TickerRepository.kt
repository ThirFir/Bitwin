package com.strone.domain.repository

import com.strone.domain.model.Ticker
import kotlinx.coroutines.flow.Flow

interface TickerRepository {
    suspend fun fetchTickerSnapshotResponse(codes: List<String>): List<Ticker>
    suspend fun fetchTickerStreamingResponse(codes: List<String>): Flow<Ticker>
}