package com.strone.domain.repository

import com.strone.domain.model.KakaoAuthResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun loginWithKaKao(): Flow<KakaoAuthResult>
}