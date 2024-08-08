package com.strone.presentation.ui.topbar.trade

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.strone.presentation.R
import com.strone.presentation.ui.LocalMarketComposition
import com.strone.presentation.ui.LocalTickerComposition
import com.strone.presentation.ui.theme.Typography
import com.strone.presentation.util.clickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TradeTopAppBar(
    modifier: Modifier,
    onNavigationIconClicked: () -> Unit = {},
) {

    val ticker = LocalTickerComposition.current
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = LocalMarketComposition.current[ticker.code]?.koreanName + "(${ticker.code})",
                style = Typography.titleSmall
            )
        },
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = stringResource(
                    id = R.string.back
                ),
                modifier = Modifier.clickable(showRipple = false) {
                    onNavigationIconClicked()
                }
            )

        },
        actions = {

        }
    )

}