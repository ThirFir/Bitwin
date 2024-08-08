package com.strone.bitwin.di

import com.strone.data.database.UserDatabase
import com.strone.data.datasource.local.UserLocalDataSource
import com.strone.data.datastore.UserPreferenceDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideUserLocalDataSource(
        userDatabase: UserDatabase,
        userPreferenceDataStore: UserPreferenceDataStore
    ) : UserLocalDataSource {
        return UserLocalDataSource(userDatabase, userPreferenceDataStore)
    }
}