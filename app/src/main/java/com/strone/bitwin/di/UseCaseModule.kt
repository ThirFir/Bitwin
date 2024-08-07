package com.strone.bitwin.di

import com.strone.data.exception.handler.OrderbookExceptionHandler
import com.strone.data.exception.handler.TickerExceptionHandler
import com.strone.data.exception.handler.UserExceptionHandler
import com.strone.domain.repository.OrderbookRepository
import com.strone.domain.repository.TickerRepository
import com.strone.domain.repository.UserRepository
import com.strone.domain.usecase.FetchOrderbookUseCase
import com.strone.domain.usecase.FetchTickerUseCase
import com.strone.domain.usecase.StopFetchingOrderbookUseCase
import com.strone.domain.usecase.asset.GetAssetUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideFetchTickerUseCase(
        tickerRepository: TickerRepository,
        exceptionHandler: TickerExceptionHandler
    ): FetchTickerUseCase = FetchTickerUseCase(tickerRepository, exceptionHandler)

    @Singleton
    @Provides
    fun provideFetchOrderbookUseCase(
        orderbookRepository: OrderbookRepository,
        exceptionHandler: OrderbookExceptionHandler
    ): FetchOrderbookUseCase = FetchOrderbookUseCase(orderbookRepository, exceptionHandler)

    @Singleton
    @Provides
    fun provideStopFetchingOrderbookUseCase(
        orderbookRepository: OrderbookRepository
    ): StopFetchingOrderbookUseCase = StopFetchingOrderbookUseCase(orderbookRepository)

    @Singleton
    @Provides
    fun provideGetAssetUseCase(
        userRepository: UserRepository,
        exceptionHandler: UserExceptionHandler
    ): GetAssetUseCase = GetAssetUseCase(userRepository, exceptionHandler)
}