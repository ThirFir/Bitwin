package com.strone.domain.usecase.login

import com.strone.domain.exception.ExceptionHandler
import com.strone.domain.exception.mapResultWith
import com.strone.domain.model.KakaoAuthResult
import com.strone.domain.qualifier.IoDispatcher
import com.strone.domain.repository.LoginRepository
import com.strone.domain.usecase.FlowResultUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginKaKaoUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    exceptionHandler: ExceptionHandler,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : FlowResultUseCase<Unit, KakaoAuthResult>(exceptionHandler, coroutineDispatcher) {

    override fun execute(params: Unit): Flow<KakaoAuthResult> {
        return loginRepository.loginWithKaKao()
    }
}