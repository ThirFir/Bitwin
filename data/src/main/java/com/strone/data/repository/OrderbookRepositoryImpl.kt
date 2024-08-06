package com.strone.data.repository

import com.strone.data.api.websocket.getSendJson
import com.strone.data.datasource.remote.OrderbookRemoteDataSource
import com.strone.data.mapper.mapStreamingResponse
import com.strone.data.request.RequestType
import com.strone.domain.model.Orderbook
import com.strone.domain.repository.OrderbookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrderbookRepositoryImpl @Inject constructor(
    private val orderbookRemoteDataSource: OrderbookRemoteDataSource
) : OrderbookRepository {

    override suspend fun fetchStreamingResponse(codes: List<String>): Flow<Orderbook> {
        val json = codes.getSendJson(RequestType.ORDERBOOK)

        return orderbookRemoteDataSource.fetchStreamingResponse(json)
            .mapStreamingResponse<Orderbook>()
    }

    override fun closeWebSocket() {
        orderbookRemoteDataSource.closeWebSocket()
    }
}