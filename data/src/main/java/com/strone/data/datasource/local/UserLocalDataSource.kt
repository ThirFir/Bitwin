package com.strone.data.datasource.local

import com.strone.data.database.UserDatabase
import com.strone.data.datasource.AssetDataSource
import com.strone.data.entity.AssetEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    private val userDatabase: UserDatabase
) : AssetDataSource {

    override fun getAsset(id: String): Flow<AssetEntity> {
        return userDatabase.assetDao().getAsset(id)
    }

    override fun insertAsset(asset: AssetEntity) {
        userDatabase.assetDao().insertAsset(asset)
    }

    override fun updateAsset(asset: AssetEntity) {
        userDatabase.assetDao().updateAsset(asset)
    }

    override fun deleteAsset(asset: AssetEntity) {
        userDatabase.assetDao().deleteAsset(asset)
    }
}