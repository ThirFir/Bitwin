package com.strone.bitwin.di

import com.strone.data.api.rest.MarketApi
import com.strone.domain.qualifier.WebSocket
import com.strone.data.api.rest.TickerApi
import com.strone.data.api.websocket.OrderbookWebSocketListener
import com.strone.data.api.websocket.TickerWebSocketListener
import com.strone.data.datasource.remote.MarketRemoteDataSource
import com.strone.data.datasource.remote.OrderbookRemoteDataSource
import com.strone.data.datasource.remote.TickerRemoteDataSource
import com.strone.data.datasource.remote.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideTickerRemoteDataSource(
        @WebSocket client: OkHttpClient,
        request: Request,
        tickerWebSocketListener: TickerWebSocketListener,
        tickerApi: TickerApi
    ) : TickerRemoteDataSource {
        return TickerRemoteDataSource(client, request, tickerWebSocketListener, tickerApi)
    }

    @Provides
    @Singleton
    fun provideOrderbookRemoteDataSource(
        @WebSocket client: OkHttpClient,
        request: Request,
        orderbookWebSocketListener: OrderbookWebSocketListener,
    ) : OrderbookRemoteDataSource {
        return OrderbookRemoteDataSource(client, request, orderbookWebSocketListener)
    }

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(
    ) : UserRemoteDataSource {
        return UserRemoteDataSource()
    }

    @Provides
    @Singleton
    fun provideMarketRemoteDataSource(
        marketApi: MarketApi
    ) : MarketRemoteDataSource {
        return MarketRemoteDataSource(marketApi)
    }
}