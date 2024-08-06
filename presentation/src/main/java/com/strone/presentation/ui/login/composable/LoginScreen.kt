package com.strone.presentation.ui.login.composable

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.strone.domain.usecase.LoginUseCase
import com.strone.presentation.R
import com.strone.presentation.model.LoginResultModel
import com.strone.presentation.ui.login.viewmodel.LoginViewModel
import com.strone.presentation.ui.main.MainActivity
import com.strone.presentation.util.clickable
import com.strone.presentation.util.finishActivity

@Composable
fun LoginScreen(
    modifier: Modifier,
    loginUseCase: LoginUseCase,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val loginResult by viewModel.login.collectAsStateWithLifecycle(LoginResultModel(false))
    Surface(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 40.dp),
                loginResult = loginResult,
                onKakaoLoginClicked = {
                    viewModel.kakaoLogin(loginUseCase)
                }
            )
        }
    }
}

@Composable
fun LoginContent(
    modifier: Modifier,
    loginResult: LoginResultModel,
    onKakaoLoginClicked: () -> Unit
) {
    val context = LocalContext.current
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.coin)
    )
    val lottieAnimatable = rememberLottieAnimatable()


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp),
            text = stringResource(id = R.string.intro_bitwin),
            style = MaterialTheme.typography.titleLarge
        )

        LottieAnimation(
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp),
            composition = composition,
            contentScale = ContentScale.FillWidth,
            progress = {
                lottieAnimatable.progress
            },
        )

        Spacer(modifier = Modifier.weight(1f))
        KakaoLoginButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onKakaoLoginClicked
        )
        Text(
            text = stringResource(id = R.string.guest_login),
            modifier = Modifier.clickable {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
                context.finishActivity()
            }
        )
    }

    LaunchedEffect(key1 = loginResult) {
        if (loginResult.isSuccess) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
            context.finishActivity()
        }
    }

    LaunchedEffect(composition) {
        lottieAnimatable.animate(
            composition = composition,
            clipSpec = LottieClipSpec.Frame(0, 90),
            initialProgress = 0f
        )
    }
}
