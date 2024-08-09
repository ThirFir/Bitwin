package com.strone.domain.usecase.asset

import com.strone.domain.exception.ExceptionHandler
import com.strone.domain.qualifier.IoDispatcher
import com.strone.domain.repository.UserRepository
import com.strone.domain.usecase.FlowResultUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DeleteAssetUseCase @Inject constructor(
    private val userRepository: UserRepository,
    exceptionHandler: ExceptionHandler,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : FlowResultUseCase<String, Unit>(exceptionHandler, coroutineDispatcher) {

    override fun execute(params: String) = userRepository.deleteAsset(params)
}