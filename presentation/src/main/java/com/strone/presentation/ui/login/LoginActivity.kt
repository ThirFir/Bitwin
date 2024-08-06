package com.strone.presentation.ui.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.strone.domain.usecase.LoginUseCase
import com.strone.presentation.ui.login.composable.LoginScreen
import com.strone.presentation.ui.theme.BitwinTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    @Inject
    lateinit var loginUseCase: LoginUseCase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BitwinTheme {
                LoginScreen(
                    modifier = Modifier.fillMaxSize(),
                    loginUseCase = loginUseCase
                )
            }
        }
    }
}