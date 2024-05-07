package com.strone.data.api.rest

import com.strone.data.constant.Constant
import com.strone.data.constant.URLConstant
import com.strone.data.response.rest.TickerSnapshotResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TickerApi {

    @GET(URLConstant.V1.Ticker.TICKER)
    suspend fun fetchAllTickers(
        @Query(Constant.MARKETS) codes: String
    ) : List<TickerSnapshotResponse>
}