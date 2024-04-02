package com.strone.bitwin.di

import com.strone.bitwin.constant.Constant
import com.strone.core.qualifier.ApiUrl
import com.strone.core.qualifier.WebSocketUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @WebSocketUrl
    @Provides
    @Singleton
    fun provideWebSocketUrl(): String {
        return Constant.SOCKET_BASE_URL
    }

    @ApiUrl
    @Provides
    @Singleton
    fun provideApiUrl(): String {
        return Constant.BASE_URL
    }
}