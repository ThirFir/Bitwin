package com.strone.data.response.auth

import com.kakao.sdk.auth.model.OAuthToken

data class KakaoAuthResponse(
    val token: OAuthToken? = null,
    val error: Throwable? = null
)