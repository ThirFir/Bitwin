package com.strone.data.api

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.strone.data.entity.AssetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AssetDao {

    @Insert
    suspend fun insertAsset(asset: AssetEntity)

    @Update
    suspend fun updateAsset(asset: AssetEntity)

    @Delete
    suspend fun deleteAsset(asset: AssetEntity)

    @Query("SELECT * FROM AssetEntity WHERE id = :id")
    suspend fun getAsset(id: String): Flow<AssetEntity>
}