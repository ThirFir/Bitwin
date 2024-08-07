package com.strone.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.squareup.moshi.Moshi
import com.strone.data.dao.AssetDao
import com.strone.data.converter.AssetTypeConverter
import com.strone.data.entity.AssetEntity

@Database(entities = [AssetEntity::class], version = 1)
@TypeConverters(AssetTypeConverter::class)
abstract class UserDatabase: RoomDatabase() {
    abstract fun assetDao(): AssetDao

    companion object {
        private var instance: UserDatabase? = null
        private const val DB_NAME = "user"

        @Synchronized
        fun getInstance(context: Context, moshi: Moshi): UserDatabase {
            return instance ?: Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                DB_NAME
            ).addTypeConverter(AssetTypeConverter(moshi)).build().also {
                instance = it
            }
        }
    }
}