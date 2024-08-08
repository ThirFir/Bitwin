package com.strone.data.datasource

import com.strone.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    val user: Flow<UserEntity?>
    fun saveUser(userEntity: UserEntity) : Flow<Unit>
}