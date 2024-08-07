package com.strone.domain.repository

import com.strone.domain.model.KakaoAuthResult
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun loginWithKaKao(): Flow<KakaoAuthResult>
}