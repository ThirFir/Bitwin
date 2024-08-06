package com.strone.bitwin.di

import com.squareup.moshi.Moshi
import com.strone.data.api.websocket.OrderbookWebSocketListener
import com.strone.data.api.websocket.TickerWebSocketListener
import com.strone.data.api.websocket.UpbitWebSocketListener
import com.strone.data.response.websocket.OrderbookResponse
import com.strone.data.response.websocket.TickerStreamingResponse
import com.strone.data.response.websocket.UpbitStreamingResponse
import dagger.Module
import dagger.Provides
import dagger.assisted.AssistedFactory
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.channels.SendChannel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WebSocketModule {

    @Provides
    @Singleton
    fun provideTickerWebSocketListener(
        moshi: Moshi
    ) : TickerWebSocketListener {
        return TickerWebSocketListener(moshi)
    }

    @Provides
    @Singleton
    fun provideOrderbookWebSocketListener(
        moshi: Moshi
    ) : OrderbookWebSocketListener {
        return OrderbookWebSocketListener(moshi)
    }
}

