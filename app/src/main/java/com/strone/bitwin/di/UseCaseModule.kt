package com.strone.bitwin.di

import com.strone.data.exception.handler.MarketExceptionHandler
import com.strone.data.exception.handler.OrderbookExceptionHandler
import com.strone.data.exception.handler.TickerExceptionHandler
import com.strone.data.exception.handler.UserExceptionHandler
import com.strone.domain.qualifier.IoDispatcher
import com.strone.domain.repository.MarketRepository
import com.strone.domain.repository.OrderbookRepository
import com.strone.domain.repository.TickerRepository
import com.strone.domain.repository.UserRepository
import com.strone.domain.usecase.CollectUserUseCase
import com.strone.domain.usecase.FetchMarketUseCase
import com.strone.domain.usecase.FetchOrderbookUseCase
import com.strone.domain.usecase.FetchTickerUseCase
import com.strone.domain.usecase.SaveUserUseCase
import com.strone.domain.usecase.StopFetchingOrderbookUseCase
import com.strone.domain.usecase.asset.GetAssetUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
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

    @Singleton
    @Provides
    fun provideCollectUserUseCase(
        userRepository: UserRepository,
        exceptionHandler: UserExceptionHandler,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): CollectUserUseCase = CollectUserUseCase(userRepository, exceptionHandler, coroutineDispatcher)

    @Singleton
    @Provides
    fun provideSaveUserUseCase(
        userRepository: UserRepository,
        exceptionHandler: UserExceptionHandler,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): SaveUserUseCase = SaveUserUseCase(userRepository, exceptionHandler, coroutineDispatcher)

    @Singleton
    @Provides
    fun provideFetchMarketUseCase(
        marketRepository: MarketRepository,
        exceptionHandler: MarketExceptionHandler,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): FetchMarketUseCase = FetchMarketUseCase(marketRepository, exceptionHandler, coroutineDispatcher)
}