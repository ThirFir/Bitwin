package com.strone.domain.usecase.asset

import com.strone.domain.exception.ExceptionHandler
import com.strone.domain.model.Asset
import com.strone.domain.qualifier.IoDispatcher
import com.strone.domain.repository.UserRepository
import com.strone.domain.usecase.FlowResultUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class InsertAssetUseCase @Inject constructor(
    private val userRepository: UserRepository,
    exceptionHandler: ExceptionHandler,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : FlowResultUseCase<Asset, Unit>(exceptionHandler, coroutineDispatcher) {

    override fun execute(params: Asset) = userRepository.insertAsset(params)
}