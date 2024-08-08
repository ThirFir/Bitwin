package com.strone.domain.usecase.login

import com.strone.domain.exception.ExceptionHandler
import com.strone.domain.qualifier.IoDispatcher
import com.strone.domain.repository.LoginRepository
import com.strone.domain.usecase.FlowResultUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginGuestUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    exceptionHandler: ExceptionHandler,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : FlowResultUseCase<Unit, Unit>(exceptionHandler, coroutineDispatcher) {

    override fun execute(params: Unit): Flow<Unit> {
        return loginRepository.loginAsGuest()
    }
}