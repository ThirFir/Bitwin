package com.strone.bitwin.di

import android.content.Context
import com.strone.data.datasource.remote.LoginRemoteDataSource
import com.strone.data.exception.handler.LoginExceptionHandler
import com.strone.data.exception.manager.NetworkExceptionHandleManager
import com.strone.data.repository.LoginRepositoryImpl
import com.strone.domain.repository.LoginRepository
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
object LoginModule {

    @Provides
    @ActivityScoped
    fun provideLoginUseCase(
        loginRepository: LoginRepository,
        exceptionHandler: LoginExceptionHandler
    ): LoginUseCase = LoginUseCase(loginRepository, exceptionHandler)

    @Provides
    @ActivityScoped
    fun provideLoginExceptionHandler(
        @ApplicationContext context: Context,
        networkExceptionHandleManager: NetworkExceptionHandleManager
    ): LoginExceptionHandler = LoginExceptionHandler(context, networkExceptionHandleManager)

    @Provides
    @ActivityScoped
    fun provideLoginRepository(
        loginRemoteDataSource: LoginRemoteDataSource
    ) : LoginRepository {
        return LoginRepositoryImpl(loginRemoteDataSource)
    }

    @Provides
    @ActivityScoped
    fun provideUserRemoteDataSource(
        @ActivityContext context: Context
    ) : LoginRemoteDataSource {
        return LoginRemoteDataSource(context)
    }
}