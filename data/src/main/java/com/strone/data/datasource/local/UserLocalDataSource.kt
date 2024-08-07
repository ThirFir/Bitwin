package com.strone.data.datasource.local

import com.strone.data.database.UserDatabase
import com.strone.data.entity.AssetEntity
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    private val userDatabase: UserDatabase
) {

    suspend fun getAsset(id: String): AssetEntity {
        return userDatabase.assetDao().getAsset(id)
    }

    suspend fun insertAsset(asset: AssetEntity) {
        userDatabase.assetDao().insertAsset(asset)
    }

    suspend fun updateAsset(asset: AssetEntity) {
        userDatabase.assetDao().updateAsset(asset)
    }

    suspend fun deleteAsset(asset: AssetEntity) {
        userDatabase.assetDao().deleteAsset(asset)
    }
}