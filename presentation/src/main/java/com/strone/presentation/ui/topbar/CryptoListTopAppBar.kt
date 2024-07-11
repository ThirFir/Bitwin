package com.strone.presentation.ui.topbar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.strone.presentation.R
import com.strone.presentation.ui.component.FilledTextField

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CryptoListTopAppBar(
    modifier: Modifier
) {
    val inputState = rememberTextFieldState()
    TopAppBar(
        title = {
            FilledTextField(
                state = inputState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                navigationIcon = painterResource(id = R.drawable.ic_search),
                label = stringResource(R.string.label_input_crypto),
                lineLimits = TextFieldLineLimits.SingleLine,
            )
        },
        modifier = modifier,
        actions = {
            IconButton(onClick = {
                // Go to notice setting
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notice),
                    contentDescription = stringResource(
                        id = R.string.action_notice_setting
                    )
                )
            }
            IconButton(onClick = {
                // Go to profile
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = stringResource(
                        id = R.string.action_profile
                    )
                )
            }
        }
    )
}