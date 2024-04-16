package com.strone.bitwin.di

import com.squareup.moshi.Moshi
import com.strone.data.api.websocket.TickerWebSocketListener
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}