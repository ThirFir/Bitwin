package com.strone.bitwin

import android.app.Application
import com.strone.core.CryptoNamespace
import com.strone.core.qualifier.IoDispatcher
import com.strone.data.api.rest.MarketApi
import com.strone.data.mapper.toMarket
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class BitwinApplication : Application() {

    @Inject
    @IoDispatcher
    lateinit var ioDispatcher: CoroutineDispatcher

    @Inject
    lateinit var marketApi: MarketApi

    override fun onCreate() {
        super.onCreate()
        initializeCryptoNamespace()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun initializeCryptoNamespace() {
        GlobalScope.launch(ioDispatcher) {
            CryptoNamespace.putMarkets(marketApi.fetchAllMarkets().map { it.toMarket() })
        }
    }
}
