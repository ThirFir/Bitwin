package com.strone.domain.repository

import com.strone.domain.model.Market

interface MarketRepository {

    suspend fun fetchAllMarkets() : List<Market>
}