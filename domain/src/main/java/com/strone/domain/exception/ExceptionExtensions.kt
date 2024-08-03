package com.strone.domain.exception

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

internal inline fun <R> runCatchWith(handler: ExceptionHandler, block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: Throwable) {
        Result.failure(handler.handleException(e))
    }
}

fun<T> Flow<T>.mapCatchWith(exceptionHandler: ExceptionHandler) : Flow<Result<T>> {
    return map {
        Result.success(it)
    }.catch {
        emit(Result.failure(exceptionHandler.handleException(it)))
    }
}