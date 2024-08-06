package com.strone.bitwin.di

import android.content.Context
import com.strone.data.exception.handler.OrderbookExceptionHandler
import com.strone.data.exception.handler.TickerExceptionHandler
import com.strone.data.exception.manager.NetworkExceptionHandleManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ErrorHandleModule {

    @Singleton
    @Provides
    fun provideTickerExceptionHandler(
        @ApplicationContext context: Context,
        networkExceptionHandleManager: NetworkExceptionHandleManager
    ): TickerExceptionHandler = TickerExceptionHandler(context, networkExceptionHandleManager)

    @Singleton
    @Provides
    fun provideOrderbookExceptionHandler(
        @ApplicationContext context: Context,
        networkExceptionHandleManager: NetworkExceptionHandleManager
    ): OrderbookExceptionHandler = OrderbookExceptionHandler(context, networkExceptionHandleManager)

    @Singleton
    @Provides
    fun provideNetworkExceptionHandleManager(
        @ApplicationContext context: Context
    ): NetworkExceptionHandleManager = NetworkExceptionHandleManager(context)
}