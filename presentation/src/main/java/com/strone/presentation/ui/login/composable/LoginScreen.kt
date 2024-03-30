package com.strone.presentation.ui.login.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.strone.presentation.R

@Composable
fun LoginScreen(modifier: Modifier) {
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
                    .padding(all = 40.dp)
            )
        }
    }
}

@Composable
fun LoginContent(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.coin)
        )
        val lottieAnimatable = rememberLottieAnimatable()

        LaunchedEffect(composition) {
            lottieAnimatable.animate(
                composition = composition,
                clipSpec = LottieClipSpec.Frame(0, 90),
                initialProgress = 0f
            )
        }

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
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    // TODO("Add feature : Kakao login")
                },
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = R.drawable.kakao_login_medium_wide),
            contentDescription = stringResource(id = R.string.kakao_button)
        )
    }
}
