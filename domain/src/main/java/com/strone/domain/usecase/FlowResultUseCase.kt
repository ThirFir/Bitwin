package com.strone.domain.usecase

import com.strone.domain.exception.ExceptionHandler
import com.strone.domain.exception.mapResultWith
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowResultUseCase<in Params, R>(
    private val exceptionHandler: ExceptionHandler,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    operator fun invoke(params: Params): Flow<Result<R>> {
        return execute(params).flowOn(coroutineDispatcher).mapResultWith(exceptionHandler)
    }

    protected abstract fun execute(params: Params): Flow<R>
}