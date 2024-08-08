package com.strone.presentation.ui.login.viewmodel

import androidx.lifecycle.viewModelScope
import com.strone.presentation.ui.BaseViewModel
import com.strone.domain.usecase.LoginUseCase
import com.strone.presentation.model.LoginResultModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

) : BaseViewModel() {

    private val _login: MutableSharedFlow<LoginResultModel> = MutableSharedFlow()
    val login: SharedFlow<LoginResultModel>
        get() = _login

    fun kakaoLogin(useCase: LoginUseCase) {
        viewModelScope.launch {
            useCase.loginWithKakao().collect {
                it.onComplete {
                    _login.emit(LoginResultModel(true))
                }
            }

        }
    }
}