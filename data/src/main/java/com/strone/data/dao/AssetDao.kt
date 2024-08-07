package com.strone.data.dao

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
    fun insertAsset(asset: AssetEntity)

    @Update
    fun updateAsset(asset: AssetEntity)

    @Delete
    fun deleteAsset(asset: AssetEntity)

    @Query("SELECT * FROM AssetEntity WHERE id = :id")
    fun getAsset(id: String): Flow<AssetEntity>
}