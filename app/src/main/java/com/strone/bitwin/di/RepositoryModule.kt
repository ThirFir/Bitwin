package com.strone.bitwin.di

import com.strone.data.datasource.remote.TickerRemoteDataSource
import com.strone.data.repository.TickerRepositoryImpl
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
    fun provideTickerRepository(
        tickerRemoteDataSource: TickerRemoteDataSource
    ) : TickerRepository {
        return TickerRepositoryImpl(tickerRemoteDataSource)
    }
}