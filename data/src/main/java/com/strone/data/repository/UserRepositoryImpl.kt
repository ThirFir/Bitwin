package com.strone.data.repository

import com.strone.data.datasource.remote.UserRemoteDataSource
import com.strone.domain.model.KakaoAuthResult
import com.strone.domain.repository.UserRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun loginWithKaKao(): Flow<KakaoAuthResult> {
        return callbackFlow {
            userRemoteDataSource.channel = this
            userRemoteDataSource.kakaoLogin()
            awaitClose {
                userRemoteDataSource.channel.close()
            }
        }.map { KakaoAuthResult(it.error) }
    }
}