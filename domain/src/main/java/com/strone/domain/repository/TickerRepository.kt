package com.strone.domain.repository

import com.strone.domain.model.Ticker
import kotlinx.coroutines.flow.Flow

interface TickerRepository {
    suspend fun getTickerResponse(): Flow<Ticker>
}