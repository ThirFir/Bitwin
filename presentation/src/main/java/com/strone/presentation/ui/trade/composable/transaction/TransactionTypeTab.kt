package com.strone.presentation.ui.trade.composable.transaction

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.stringResource
import com.strone.presentation.state.TransactionTabState
import com.strone.presentation.ui.theme.ColorBackgroundGray
import com.strone.presentation.ui.theme.ColorFall
import com.strone.presentation.ui.theme.ColorRise
import com.strone.presentation.ui.theme.ColorTextGray
import com.strone.presentation.util.clickable
import kotlin.math.sqrt

@Composable
fun TransactionTypeTab(
    modifier: Modifier,
    selectedTab: TransactionTabState,
    onTabSelected: (TransactionTabState) -> Unit
) {

    Box(
        modifier = modifier
    ) {
        SellTab(modifier = Modifier
            .fillMaxSize()
            .clickable(
                showRipple = false
            ) {
                onTabSelected(TransactionTabState.SELL)
            },
            isSelected = selectedTab == TransactionTabState.SELL
        )

        BuyTab(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.5f)
            .clickable(
                showRipple = false,
            ) {
                onTabSelected(TransactionTabState.BUY)
            },
            isSelected = selectedTab == TransactionTabState.BUY
        )
    }
}

@Composable
fun BuyTab(
    modifier: Modifier,
    isSelected: Boolean
) {
    var backgroundColor by remember {
        mutableStateOf(ColorRise)
    }
    var textColor by remember {
        mutableStateOf(Color.White)
    }

    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = modifier.fillMaxSize()) {
            drawRoundRect(
                color = backgroundColor,
                size = Size(width = size.width * .2f, height = size.height - 10),
                topLeft = Offset(x = 0f, y = 5f),
                cornerRadius = CornerRadius(20f)
            )

            drawRoundRect(
                color = backgroundColor,
                size = Size(width = size.width * .8f, height = size.height - 10),
                topLeft = Offset(x = size.width * .1f, y = 5f),
                cornerRadius = CornerRadius(0f)
            )

            rotate(
                degrees = 45f,
                pivot = Offset(x = size.width * 0.9f, y = size.height / 2)
            ) {
                drawRoundRect(
                    color = backgroundColor,
                    size = Size(
                        width = size.height / 2 * sqrt(2f),
                        height = size.height / 2 * sqrt(2f)
                    ),
                    topLeft = Offset(
                        x = size.width * 0.9f - size.height * (sqrt(2f) / 4),
                        y = size.height * (1 - 1 / sqrt(2f)) / 2
                    ),
                    cornerRadius = CornerRadius(12f)
                )
            }
        }

        Text(
            text = stringResource(id = TransactionTabState.BUY.title),
            color = textColor
        )
    }

    LaunchedEffect(key1 = isSelected) {
        if(isSelected) {
            backgroundColor = ColorRise
            textColor = Color.White
        } else {
            backgroundColor = ColorBackgroundGray
            textColor = ColorTextGray
        }
    }
}

@Composable
fun SellTab(
    modifier: Modifier,
    isSelected: Boolean
) {
    var backgroundColor by remember {
        mutableStateOf(ColorBackgroundGray)
    }
    var textColor by remember {
        mutableStateOf(ColorTextGray)
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterEnd
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRoundRect(
                color = backgroundColor,
                size = Size(width = size.width, height = size.height - 10),
                topLeft = Offset(x = 0f, y = 5f),
                cornerRadius = CornerRadius(20f)
            )
        }

        Box(
            modifier = Modifier.fillMaxHeight().fillMaxWidth(0.5f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = TransactionTabState.SELL.title),
                color = textColor
            )
        }
    }

    LaunchedEffect(key1 = isSelected) {
        if(isSelected) {
            backgroundColor = ColorFall
            textColor = Color.White
        } else {
            backgroundColor = ColorBackgroundGray
            textColor = ColorTextGray
        }
    }
}