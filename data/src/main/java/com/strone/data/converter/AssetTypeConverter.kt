package com.strone.data.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.strone.data.entity.HoldingCryptoEntity

@ProvidedTypeConverter
class AssetTypeConverter(
    private val moshi: Moshi
) {

    private val adapter by lazy {
        val listType = Types.newParameterizedType(List::class.java, HoldingCryptoEntity::class.java)
        moshi.adapter<List<HoldingCryptoEntity>>(listType)
    }

    @TypeConverter
    fun fromString(value: String): List<HoldingCryptoEntity>? {
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(list: List<HoldingCryptoEntity>): String {
        return adapter.toJson(list)
    }
}