package com.strone.data.repository

import com.strone.data.datasource.local.LoginLocalDataSource
import com.strone.data.datasource.remote.LoginRemoteDataSource
import com.strone.domain.model.KakaoAuthResult
import com.strone.domain.model.User
import com.strone.domain.repository.LoginRepository
import com.strone.domain.repository.UserRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val loginRemoteDataSource: LoginRemoteDataSource,
    private val loginLocalDataSource: LoginLocalDataSource
) : LoginRepository {

    override fun loginWithKaKao(): Flow<KakaoAuthResult> {
        return callbackFlow {
            loginRemoteDataSource.channel = this
            loginRemoteDataSource.kakaoLogin()
            awaitClose {
                loginRemoteDataSource.channel.close()
            }
        }.map { KakaoAuthResult(it.error) }
    }

    override fun loginAsGuest(): Flow<Unit> {
        val uuid = UUID.randomUUID().toString()
        return userRepository.saveUser(User(uuid, "Guest" + uuid.substring(0, 8), true))
    }
}