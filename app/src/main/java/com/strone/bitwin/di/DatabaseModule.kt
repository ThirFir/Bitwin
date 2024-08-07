package com.strone.bitwin.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.strone.data.database.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideUserDatabase(
        @ApplicationContext context: Context,
        moshi: Moshi
    ): UserDatabase {
        return UserDatabase.getInstance(context, moshi)
    }
}