package com.strone.bitwin

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.strone.core.CryptoNamespace
import com.strone.domain.qualifier.IoDispatcher
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
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
        initializeCryptoNamespace()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun initializeCryptoNamespace() {
        GlobalScope.launch(ioDispatcher) {
            CryptoNamespace.putMarkets(marketApi.fetchAllMarkets().map { it.toMarket() })
        }
    }
}
