package com.strone.data.exception.handler

import android.content.Context
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.strone.data.R
import com.strone.data.exception.manager.NetworkExceptionHandleManager
import com.strone.domain.exception.ExceptionHandler
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LoginExceptionHandler @Inject constructor(
    @ApplicationContext private val context: Context,
    private val networkExceptionHandleManager: NetworkExceptionHandleManager
) : ExceptionHandler {

        override fun handleMessage(e: Throwable): String {
            return context.run {
                when (e) {
                    is ClientError -> {
                        when (e.reason) {
                            ClientErrorCause.Cancelled -> getString(R.string.error_user_cancelled)
                            else -> getString(R.string.error_login_failed)
                        }
                    }
                    else -> networkExceptionHandleManager.handleCommonMessage(e)
                }
            }
        }
}