package com.strone.data.datasource.remote

import com.strone.data.datasource.UserDataSource
import com.strone.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(

) : UserDataSource {
    override val user: Flow<UserEntity?>
        get() = TODO("Get user from Firebase")

    override fun saveUser(userEntity: UserEntity): Flow<Unit> {
        TODO("Save user to Firebase")
    }
}