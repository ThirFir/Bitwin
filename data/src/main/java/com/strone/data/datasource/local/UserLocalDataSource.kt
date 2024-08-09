package com.strone.data.datasource.local

import com.strone.data.database.UserDatabase
import com.strone.data.datasource.AssetDataSource
import com.strone.data.datasource.UserDataSource
import com.strone.data.datastore.UserPreferenceDataStore
import com.strone.data.entity.AssetEntity
import com.strone.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    private val userDatabase: UserDatabase,
    private val userPreferenceDataStore: UserPreferenceDataStore
) : AssetDataSource, UserDataSource {

    override val user: Flow<UserEntity?> = userPreferenceDataStore.user

    override fun saveUser(userEntity: UserEntity): Flow<Unit> {
        return userPreferenceDataStore.saveUser(userEntity)
    }

    override fun getAsset(id: String): Flow<AssetEntity> {
        return userDatabase.assetDao().getAsset(id)
    }

    override fun insertAsset(asset: AssetEntity) {
        userDatabase.assetDao().insertAsset(asset)
    }

    override fun updateAsset(asset: AssetEntity) {
        userDatabase.assetDao().updateAsset(asset)
    }

    override fun deleteAsset(id: String) {
        userDatabase.assetDao().deleteAsset(id)
    }
}