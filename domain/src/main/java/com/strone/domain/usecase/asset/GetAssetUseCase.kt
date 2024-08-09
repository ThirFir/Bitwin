package com.strone.domain.usecase.asset

import com.strone.domain.exception.ExceptionHandler
import com.strone.domain.model.Asset
import com.strone.domain.qualifier.IoDispatcher
import com.strone.domain.repository.UserRepository
import com.strone.domain.usecase.FlowResultUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAssetUseCase @Inject constructor(
    private val userRepository: UserRepository,
    exceptionHandler: ExceptionHandler,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : FlowResultUseCase<String, Asset>(exceptionHandler, coroutineDispatcher) {

    override fun execute(params: String): Flow<Asset> {
        return userRepository.getAsset(params)
    }
}