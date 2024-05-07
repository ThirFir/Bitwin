package com.strone.bitwin.di

import com.strone.core.qualifier.WebSocket
import com.strone.data.api.rest.TickerApi
import com.strone.data.api.websocket.TickerWebSocketListener
import com.strone.data.datasource.remote.TickerRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

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
}