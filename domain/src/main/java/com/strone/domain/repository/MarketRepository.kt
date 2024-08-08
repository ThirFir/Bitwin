package com.strone.domain.repository

import com.strone.domain.model.Market
import kotlinx.coroutines.flow.Flow

interface MarketRepository {
    fun fetchAllMarkets() : Flow<List<Market>>
}