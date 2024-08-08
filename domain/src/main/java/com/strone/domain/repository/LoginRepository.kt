package com.strone.domain.repository

import com.strone.domain.model.KakaoAuthResult
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun loginWithKaKao(): Flow<KakaoAuthResult>
    fun loginAsGuest(): Flow<Unit>
}