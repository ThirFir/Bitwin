package com.strone.data.datasource

import com.strone.data.entity.AssetEntity
import kotlinx.coroutines.flow.Flow

interface AssetDataSource {
    fun getAsset(id: String): Flow<AssetEntity>
    fun insertAsset(asset: AssetEntity)
    fun updateAsset(asset: AssetEntity)
    fun deleteAsset(asset: AssetEntity)
}