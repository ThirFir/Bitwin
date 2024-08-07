package com.strone.domain.repository

import com.strone.domain.model.Asset
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getAsset(id: String): Flow<Asset>
    suspend fun insertAsset(asset: Asset)
    suspend fun updateAsset(asset: Asset)
    suspend fun deleteAsset(asset: Asset)
}