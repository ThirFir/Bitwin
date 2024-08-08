package com.strone.bitwin.di

import com.strone.domain.qualifier.ApplicationScope
import com.strone.domain.usecase.CollectUserUseCase
import com.strone.domain.usecase.SaveUserUseCase
import com.strone.presentation.delegate.UserDelegate
import com.strone.presentation.delegate.UserDelegateImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DelegateModule {

    @Provides
    @Singleton
    fun provideUserDelegate(
        @ApplicationScope coroutineScope: CoroutineScope,
        collectUserUseCase: CollectUserUseCase,
        saveUserUseCase: SaveUserUseCase
    ): UserDelegate = UserDelegateImpl(coroutineScope, collectUserUseCase, saveUserUseCase)
}