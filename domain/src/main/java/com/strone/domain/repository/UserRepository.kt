package com.strone.domain.repository

import com.strone.domain.model.Asset
import com.strone.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val user: Flow<User?>
    suspend fun saveUser(user: User): Flow<Unit>
    suspend fun getAsset(id: String): Flow<Asset>
    suspend fun insertAsset(asset: Asset): Flow<Unit>
    suspend fun updateAsset(asset: Asset): Flow<Unit>
    suspend fun deleteAsset(asset: Asset): Flow<Unit>
}