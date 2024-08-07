package com.strone.domain.usecase.asset

import com.strone.domain.exception.ExceptionHandler
import com.strone.domain.exception.mapResultWith
import com.strone.domain.model.Asset
import com.strone.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAssetUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val exceptionHandler: ExceptionHandler
) {

    suspend operator fun invoke(id: String) : Flow<Result<Asset>> {
        return userRepository.getAsset(id).mapResultWith(exceptionHandler)
    }
}