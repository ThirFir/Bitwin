package com.strone.presentation.ui.trade.composable.orderbook

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.strone.domain.model.type.ChangeType
import com.strone.domain.model.type.OrderType
import com.strone.presentation.model.OrderbookModel
import com.strone.presentation.ui.component.CryptoColoredText
import com.strone.presentation.ui.theme.ColorFallLight
import com.strone.presentation.ui.theme.ColorRiseLight
import com.strone.presentation.util.minus
import com.strone.presentation.util.toDisplayedDoubleFormat

@Composable
fun OrderbookUnitItem(
    modifier: Modifier,
    orderbookUnit: OrderbookModel.OrderbookUnitModel,
    sizeRatio: Float
) {
    var priceFontSize by remember { mutableStateOf(12.sp) }
    var sizeFontSize by remember { mutableStateOf(12.sp) }
    var readyToDraw by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.height(IntrinsicSize.Min).drawWithContent {
            if(readyToDraw) drawContent()
        },
        contentAlignment = Alignment.CenterEnd
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(sizeRatio)
                .background(
                    color = if (orderbookUnit.orderType == OrderType.ASK)
                        ColorFallLight else ColorRiseLight,
                    shape = RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp)
                )
        )

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CryptoColoredText(
                modifier = Modifier.padding(end = 12.dp),
                text = orderbookUnit.price.toDisplayedDoubleFormat(),
                softWrap = false,
                change = if (orderbookUnit.orderType == OrderType.ASK) ChangeType.FALL else ChangeType.RISE,
                fontSize = priceFontSize,
                onTextLayout = {
                    if (it.didOverflowWidth) {
                        priceFontSize -= 1f
                    } else {
                        readyToDraw = true
                    }
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.drawWithContent {
                    if(readyToDraw) drawContent()
                },
                text = orderbookUnit.size.toDisplayedDoubleFormat(),
                softWrap = false,
                fontWeight = FontWeight.Bold,
                fontSize = sizeFontSize,
                onTextLayout = {
                    if (it.didOverflowWidth) {
                        sizeFontSize -= 1f
                    } else {
                        readyToDraw = true
                    }
                }
            )
        }
    }
}
