package com.strone.data.datasource.remote

import com.strone.data.api.rest.MarketApi
import com.strone.data.response.rest.MarketResponse
import javax.inject.Inject

class MarketRemoteDataSource @Inject constructor(
    private val marketApi: MarketApi
) {
    suspend fun fetchAllMarkets() : List<MarketResponse> {
        return marketApi.fetchAllMarkets()
    }
}