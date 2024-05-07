package com.strone.data.api.rest

import com.strone.data.constant.URLConstant
import com.strone.data.response.rest.MarketResponse
import retrofit2.http.GET

interface MarketApi {

    @GET(URLConstant.V1.Market.ALL)
    suspend fun fetchAllMarkets() : List<MarketResponse>
}