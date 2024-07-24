package com.strone.data.repository

import com.strone.data.datasource.remote.OrderbookRemoteDataSource
import com.strone.data.mapper.toOrderbook
import com.strone.data.request.RequestType
import com.strone.data.response.websocket.OrderbookResponse
import com.strone.data.util.getSendJson
import com.strone.domain.model.Orderbook
import com.strone.domain.repository.OrderbookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OrderbookRepositoryImpl @Inject constructor(
    private val orderbookRemoteDataSource: OrderbookRemoteDataSource
) : OrderbookRepository {

    override suspend fun fetchStreamingResponse(codes: List<String>): Flow<Orderbook> {
        val json = codes.getSendJson(RequestType.ORDERBOOK)

        return orderbookRemoteDataSource.fetchStreamingResponse(json)
            .map(OrderbookResponse::toOrderbook)
    }

    override fun closeWebSocket(reason: String) {
        orderbookRemoteDataSource.closeWebSocket(reason)
    }
}