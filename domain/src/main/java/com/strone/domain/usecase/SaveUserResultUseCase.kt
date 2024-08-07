package com.strone.domain.usecase

import com.strone.domain.exception.ExceptionHandler
import com.strone.domain.model.User
import com.strone.domain.qualifier.IoDispatcher
import com.strone.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveUserResultUseCase @Inject constructor(
    private val userRepository: UserRepository,
    exceptionHandler: ExceptionHandler,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : FlowResultUseCase<User, Unit>(exceptionHandler, coroutineDispatcher) {

    override suspend fun execute(params: User): Flow<Unit> {
        return userRepository.saveUser(params)
    }
}