package com.strone.data.exception.handler

import android.content.Context
import com.strone.domain.exception.ExceptionHandler
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserExceptionHandler @Inject constructor(
    @ApplicationContext private val context: Context
) : ExceptionHandler {

    override fun handleMessage(e: Throwable): String {
        return context.run {
            when (e) {
                else -> "TODO ERROR MESSAGE"
            }
        }
    }
}