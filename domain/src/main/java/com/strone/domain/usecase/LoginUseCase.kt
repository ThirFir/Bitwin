package com.strone.domain.usecase

import com.strone.domain.exception.ExceptionHandler
import com.strone.domain.exception.mapResultWith
import com.strone.domain.model.KakaoAuthResult
import com.strone.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val exceptionHandler: ExceptionHandler
) {

    suspend fun loginWithKakao() : Flow<Result<KakaoAuthResult>> {
        return loginRepository.loginWithKaKao()
            .mapResultWith(exceptionHandler)
    }
}