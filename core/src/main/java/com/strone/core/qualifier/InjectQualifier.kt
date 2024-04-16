package com.strone.core.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WebSocket

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RestApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Ticker