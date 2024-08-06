package com.strone.domain.usecase

import com.strone.domain.exception.ExceptionHandler
import com.strone.domain.exception.mapResultWith
import com.strone.domain.model.KakaoAuthResult
import com.strone.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val exceptionHandler: ExceptionHandler
) {

    suspend fun loginWithKakao() : Flow<Result<KakaoAuthResult>> {
        return userRepository.loginWithKaKao()
            .mapResultWith(exceptionHandler)
    }
}