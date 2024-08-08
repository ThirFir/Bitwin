package com.strone.domain.repository

import com.strone.domain.model.Asset
import com.strone.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val user: Flow<User>
    fun saveUser(user: User): Flow<Unit>
    fun getAsset(id: String): Flow<Asset>
    fun insertAsset(asset: Asset): Flow<Unit>
    fun updateAsset(asset: Asset): Flow<Unit>
    fun deleteAsset(asset: Asset): Flow<Unit>
}