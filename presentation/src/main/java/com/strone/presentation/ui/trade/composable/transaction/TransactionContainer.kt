package com.strone.presentation.ui.trade.composable.transaction

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.strone.presentation.model.TickerModel
import com.strone.presentation.state.TransactionTabState
import com.strone.presentation.ui.theme.ColorBackgroundGray
import com.strone.presentation.ui.theme.ColorFall
import com.strone.presentation.ui.theme.ColorRise
import com.strone.presentation.ui.theme.ColorTextGray
import com.strone.presentation.util.clickable
import kotlin.math.sqrt

@Composable
fun TransactionContainer(
    modifier: Modifier,
    ticker: TickerModel,
) {

    var selectedTab by remember {
        mutableStateOf(TransactionTabState.BUY)
    }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TransactionTypeTab(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                }
            )
        }
    }
}
