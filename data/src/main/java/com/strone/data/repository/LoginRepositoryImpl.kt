package com.strone.data.repository

import com.strone.data.datasource.remote.LoginRemoteDataSource
import com.strone.domain.model.KakaoAuthResult
import com.strone.domain.repository.LoginRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource
) : LoginRepository {

    override suspend fun loginWithKaKao(): Flow<KakaoAuthResult> {
        return callbackFlow {
            loginRemoteDataSource.channel = this
            loginRemoteDataSource.kakaoLogin()
            awaitClose {
                loginRemoteDataSource.channel.close()
            }
        }.map { KakaoAuthResult(it.error) }
    }
}