package com.strone.data.repository

import com.strone.data.datasource.local.UserLocalDataSource
import com.strone.data.entity.AssetEntity
import com.strone.data.mapper.toAsset
import com.strone.data.mapper.toAssetEntity
import com.strone.data.datastore.UserPreferenceDataStore
import com.strone.domain.model.Asset
import com.strone.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val userPreferenceDataStore: UserPreferenceDataStore
) : UserRepository, UserPreferenceDataStore by userPreferenceDataStore {

    override suspend fun getAsset(id: String): Flow<Asset> {
        return userLocalDataSource.getAsset(id).map(AssetEntity::toAsset)
    }

    override suspend fun insertAsset(asset: Asset): Flow<Unit> {
        return flow {
            userLocalDataSource.insertAsset(asset.toAssetEntity())
        }
    }

    override suspend fun updateAsset(asset: Asset): Flow<Unit> {
        return flow {
            userLocalDataSource.updateAsset(asset.toAssetEntity())
        }
    }

    override suspend fun deleteAsset(asset: Asset): Flow<Unit> {
        return flow {
            userLocalDataSource.deleteAsset(asset.toAssetEntity())
        }
    }
}
