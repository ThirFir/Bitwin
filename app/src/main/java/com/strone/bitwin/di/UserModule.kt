package com.strone.bitwin.di

import android.content.Context
import com.strone.data.datasource.remote.UserRemoteDataSource
import com.strone.data.exception.handler.UserExceptionHandler
import com.strone.data.exception.manager.NetworkExceptionHandleManager
import com.strone.data.repository.UserRepositoryImpl
import com.strone.domain.repository.UserRepository
import com.strone.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object UserModule {

    @Provides
    @ActivityScoped
    fun provideLoginUseCase(
        userRepository: UserRepository,
        exceptionHandler: UserExceptionHandler
    ): LoginUseCase = LoginUseCase(userRepository, exceptionHandler)

    @Provides
    @ActivityScoped
    fun provideUserExceptionHandler(
        @ApplicationContext context: Context,
        networkExceptionHandleManager: NetworkExceptionHandleManager
    ): UserExceptionHandler = UserExceptionHandler(context, networkExceptionHandleManager)

    @Provides
    @ActivityScoped
    fun provideUserRepository(
        userRemoteDataSource: UserRemoteDataSource
    ) : UserRepository {
        return UserRepositoryImpl(userRemoteDataSource)
    }

    @Provides
    @ActivityScoped
    fun provideUserRemoteDataSource(
        @ActivityContext context: Context
    ) : UserRemoteDataSource {
        return UserRemoteDataSource(context)
    }
}