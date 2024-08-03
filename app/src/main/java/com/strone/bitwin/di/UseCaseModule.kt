package com.strone.bitwin.di

import com.strone.domain.repository.OrderbookRepository
import com.strone.domain.repository.TickerRepository
import com.strone.domain.usecase.FetchOrderbookUseCase
import com.strone.domain.usecase.FetchTickerUseCase
import com.strone.domain.usecase.StopFetchingOrderbookUseCase
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
    ): FetchTickerUseCase = FetchTickerUseCase(tickerRepository)

    @Singleton
    @Provides
    fun provideStopFetchingOrderbookUseCase(
        orderbookRepository: OrderbookRepository
    ): StopFetchingOrderbookUseCase = StopFetchingOrderbookUseCase(orderbookRepository)

    @Singleton
    @Provides
    fun provideFetchOrderbookUseCase(
        orderbookRepository: OrderbookRepository
    ): FetchOrderbookUseCase = FetchOrderbookUseCase(orderbookRepository)
}