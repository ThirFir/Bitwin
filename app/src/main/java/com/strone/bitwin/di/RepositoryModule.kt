package com.strone.bitwin.di

import com.strone.data.datasource.remote.MarketRemoteDataSource
import com.strone.data.datasource.remote.TickerRemoteDataSource
import com.strone.data.repository.MarketRepositoryImpl
import com.strone.data.repository.TickerRepositoryImpl
import com.strone.domain.repository.MarketRepository
import com.strone.domain.repository.TickerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMarketRepository(
        marketRemoteDataSource: MarketRemoteDataSource
    ) : MarketRepository {
        return MarketRepositoryImpl(marketRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideTickerRepository(
        tickerRemoteDataSource: TickerRemoteDataSource
    ) : TickerRepository {
        return TickerRepositoryImpl(tickerRemoteDataSource)
    }
}