package com.strone.bitwin.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.strone.bitwin.BuildConfig
import com.strone.bitwin.constant.Constant
import com.strone.core.qualifier.RestApi
import com.strone.core.qualifier.Ticker
import com.strone.core.qualifier.WebSocket
import com.strone.data.api.websocket.TickerWebSocketListener
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Ticker
    @Provides
    @Singleton
    fun provideTickerWebSocket(
        @WebSocket okHttpClient: OkHttpClient,
        request: Request,
        tickerWebSocketListener: TickerWebSocketListener
    ) : okhttp3.WebSocket {
        return okHttpClient.newWebSocket(request, tickerWebSocketListener).also {
            okHttpClient.dispatcher.executorService.shutdown()
        }
    }

    @Provides
    @Singleton
    fun provideTickerWebSocketListener(
        moshi: Moshi
    ) : TickerWebSocketListener {
        return TickerWebSocketListener(moshi)
    }

    @Provides
    @Singleton
    fun provideWebSocketRequest(
        @WebSocket baseUrl: String,
    ) : Request {
        return Request.Builder()
            .url(baseUrl)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        @RestApi baseUrl: String,
        @RestApi okHttpClient: OkHttpClient,
        moshi: Moshi
    ) : Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @WebSocket
    @Provides
    @Singleton
    fun provideWebSocketClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ) : OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(10, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
            addInterceptor(httpLoggingInterceptor)
            dispatcher(Dispatcher().apply {
                maxRequests = 1
            })
            pingInterval(30, TimeUnit.SECONDS)
        }.build()
    }

    @RestApi
    @Provides
    @Singleton
    fun provideRestApiClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @WebSocket
    @Provides
    @Singleton
    fun provideWebSocketUrl(): String {
        return Constant.SOCKET_BASE_URL
    }

    @RestApi
    @Provides
    @Singleton
    fun provideApiUrl(): String {
        return Constant.BASE_URL
    }

    @Provides
    @Singleton
    fun provideMoshi() : Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}