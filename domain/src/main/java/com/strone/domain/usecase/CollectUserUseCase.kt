package com.strone.domain.usecase

import com.strone.domain.exception.ExceptionHandler
import com.strone.domain.model.User
import com.strone.domain.qualifier.IoDispatcher
import com.strone.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CollectUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    exceptionHandler: ExceptionHandler,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : FlowResultUseCase<Unit, User?>(exceptionHandler, coroutineDispatcher) {

    override fun execute(params: Unit): Flow<User?> {
        return userRepository.user
    }
}