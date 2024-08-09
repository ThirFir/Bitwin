package com.strone.presentation.ui.cryptoList.composable

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.strone.presentation.state.compose.CryptoTabState
import com.strone.presentation.ui.theme.ColorPrimary
import com.strone.presentation.ui.theme.Typography
import com.strone.presentation.util.clickable

@Composable
fun CryptoTab(
    modifier: Modifier,
    currentTabState: CryptoTabState,
    onTabSelected: (CryptoTabState) -> Unit = {}
) {
    val animationDuration = 200

    Row(
        modifier = modifier,
    ) {
        for(tab in CryptoTabState.entries) {
            Column(
                modifier = Modifier
                    .width(IntrinsicSize.Min)
                    .clickable(showRipple = false, onClick = {
                        onTabSelected(tab)
                    }),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = tab.title),
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .padding(horizontal = 8.dp),
                    style = Typography.titleSmall
                )

                val dividerWidth by animateFloatAsState(
                    targetValue = if (currentTabState == tab) 1f else 0f,
                    animationSpec = tween(durationMillis = animationDuration),
                    label = ""
                )

                HorizontalDivider(
                    modifier = Modifier
                        .padding(vertical = 6.dp, horizontal = 10.dp)
                        .fillMaxWidth(dividerWidth),
                    color = ColorPrimary,
                    thickness = 4.dp
                )
            }
        }
    }
}