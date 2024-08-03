package com.strone.data.exception.handler

import android.content.Context
import com.strone.data.exception.manager.NetworkExceptionHandleManager
import com.strone.domain.exception.ExceptionHandler
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TickerExceptionHandler @Inject constructor(
    @ApplicationContext private val context: Context,
    private val networkExceptionHandleManager: NetworkExceptionHandleManager
) : ExceptionHandler {

    override fun handleMessage(e: Throwable): String {
        return context.run {
            when (e) {
                else -> networkExceptionHandleManager.handleCommonMessage(e)
            }
        }
    }
}