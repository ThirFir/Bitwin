package com.strone.domain.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WebSocket

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RestApi