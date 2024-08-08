package com.strone.data.datasource.remote

import com.strone.data.api.rest.MarketApi
import javax.inject.Inject

class MarketRemoteDataSource @Inject constructor(
    private val marketApi: MarketApi
) {
    suspend fun fetchAllMarkets() = marketApi.fetchAllMarkets()
}