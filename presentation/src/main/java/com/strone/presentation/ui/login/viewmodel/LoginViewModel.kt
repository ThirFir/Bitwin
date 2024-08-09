package com.strone.presentation.ui.login.viewmodel

import androidx.lifecycle.viewModelScope
import com.strone.domain.usecase.login.LoginGuestUseCase
import com.strone.presentation.ui.BaseViewModel
import com.strone.domain.usecase.login.LoginKaKaoUseCase
import com.strone.presentation.delegate.UserDelegate
import com.strone.presentation.model.LoginResultModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    userDelegate: UserDelegate
) : BaseViewModel(), UserDelegate by userDelegate {

    private val _login: MutableSharedFlow<LoginResultModel> = MutableSharedFlow()
    val login: SharedFlow<LoginResultModel>
        get() = _login

    init {
        viewModelScope.launchWithUiState {
            user.collect {
                _login.emit(LoginResultModel(it.id.isNotEmpty()))
            }
        }
    }
    fun kakaoLogin(loginKaKaoUseCase: LoginKaKaoUseCase) {
        viewModelScope.launch {
            loginKaKaoUseCase(Unit).collect {
                it.onComplete {
                    _login.emit(LoginResultModel(true))
                }
            }
        }
    }

    fun guestLogin(loginGuestUseCase: LoginGuestUseCase) {
        viewModelScope.launch {
            loginGuestUseCase(Unit).collect {
                it.onComplete {
                    _login.emit(LoginResultModel(true))
                }
            }
        }
    }
}