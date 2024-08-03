package com.strone.data.exception.manager

interface ExceptionHandleManager {
    fun handleCommonMessage(exception: Throwable): String
}