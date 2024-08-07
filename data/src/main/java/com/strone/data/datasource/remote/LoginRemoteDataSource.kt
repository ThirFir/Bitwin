package com.strone.data.datasource.remote

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.strone.data.response.auth.KakaoAuthResponse
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.channels.ProducerScope
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor(
    @ActivityContext private val context: Context
) {

    lateinit var channel: ProducerScope<KakaoAuthResponse>

    fun kakaoLogin() {
        UserApiClient.instance.run {
            if (isKakaoTalkLoginAvailable(context)) {
                loginWithKakaoTalk(context) { token, error ->
                    if (error != null) { // Handle error
//                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
//                            // 사용자의 로그인 취소
//                        }
                        loginWithKakaoAccount(context, callback = ::loginWithKakaoAccountCallback)
                    } else if (token != null) {  // Handle success
                        channel.trySend(KakaoAuthResponse(token, null))
                    }
                }
            } else {
                loginWithKakaoAccount(context, callback = ::loginWithKakaoAccountCallback)
            }
        }
    }

    private fun loginWithKakaoAccountCallback(token: OAuthToken?, error: Throwable?) {
        channel.trySend(KakaoAuthResponse(token, error))
    }
}