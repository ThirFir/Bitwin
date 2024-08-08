package com.strone.bitwin.di

import android.content.Context
import com.strone.data.datasource.local.LoginLocalDataSource
import com.strone.data.datasource.remote.LoginRemoteDataSource
import com.strone.data.exception.handler.LoginExceptionHandler
import com.strone.data.exception.manager.NetworkExceptionHandleManager
import com.strone.data.repository.LoginRepositoryImpl
import com.strone.domain.qualifier.IoDispatcher
import com.strone.domain.repository.LoginRepository
import com.strone.domain.repository.UserRepository
import com.strone.domain.usecase.login.LoginGuestUseCase
import com.strone.domain.usecase.login.LoginKaKaoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object ActivityComponentLoginModule {

    @Provides
    @ActivityScoped
    fun provideLoginKakaoUseCase(
        loginRepository: LoginRepository,
        exceptionHandler: LoginExceptionHandler,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): LoginKaKaoUseCase = LoginKaKaoUseCase(loginRepository, exceptionHandler, coroutineDispatcher)

    @Provides
    @ActivityScoped
    fun provideLoginExceptionHandler(
        @ApplicationContext context: Context,
        networkExceptionHandleManager: NetworkExceptionHandleManager
    ): LoginExceptionHandler = LoginExceptionHandler(context, networkExceptionHandleManager)

    @Provides
    @ActivityScoped
    fun provideLoginRepository(
        userRepository: UserRepository,
        loginRemoteDataSource: LoginRemoteDataSource,
        loginLocalDataSource: LoginLocalDataSource
    ) : LoginRepository {
        return LoginRepositoryImpl(userRepository, loginRemoteDataSource, loginLocalDataSource)
    }

    @Provides
    @ActivityScoped
    fun provideUserRemoteDataSource(
        @ActivityContext context: Context
    ) : LoginRemoteDataSource {
        return LoginRemoteDataSource(context)
    }

    @Provides
    @ActivityScoped
    fun provideUserLocalDataSource(
    ) : LoginLocalDataSource {
        return LoginLocalDataSource()
    }

    @Provides
    @ActivityScoped
    fun provideLoginGuestUseCase(
        loginRepository: LoginRepository,
        exceptionHandler: LoginExceptionHandler,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): LoginGuestUseCase = LoginGuestUseCase(loginRepository, exceptionHandler, coroutineDispatcher)
}
