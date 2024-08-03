package com.strone.domain.exception

interface ExceptionHandler {
    fun handleException(exception: Throwable): Throwable {
        return exception.copy(
            message = handleMessage(exception),
            cause = exception.cause
        )
    }
    fun handleMessage(e: Throwable): String
}

fun Throwable.copy(message: String? = this.message, cause: Throwable? = this.cause): Throwable {
    return Throwable(message, cause)
}
