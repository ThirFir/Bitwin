package com.strone.presentation.delegate

import com.strone.domain.qualifier.ApplicationScope
import com.strone.domain.usecase.CollectUserUseCase
import com.strone.domain.usecase.SaveUserUseCase
import com.strone.presentation.mapper.toUser
import com.strone.presentation.mapper.toUserModel
import com.strone.presentation.model.UserModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

interface UserDelegate {
    val user: StateFlow<Result<UserModel?>>
    fun saveUser(userModel: UserModel): Flow<Result<Unit>>
}

class UserDelegateImpl @Inject constructor(
    @ApplicationScope coroutineScope: CoroutineScope,
    collectUserUseCase: CollectUserUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : UserDelegate {

    override val user: StateFlow<Result<UserModel?>> = collectUserUseCase(Unit)
        .map {
            runCatching {
                it.getOrThrow().toUserModel()
            }
        }.stateIn(scope = coroutineScope, started = SharingStarted.Eagerly, initialValue = Result.success(null))


    override fun saveUser(userModel: UserModel): Flow<Result<Unit>> {
        return saveUserUseCase(userModel.toUser())
    }
}