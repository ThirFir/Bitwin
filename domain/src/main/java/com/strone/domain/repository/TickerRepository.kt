package com.strone.domain.repository

import com.strone.domain.model.Ticker
import kotlinx.coroutines.flow.Flow

interface TickerRepository {
    suspend fun fetchTickerResponse(codes: List<String>): Flow<Ticker>
}