package com.strone.presentation.ui.trade.composable.transaction

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.strone.presentation.R
import com.strone.presentation.ui.component.textfield.FilledCentralTextField
import com.strone.presentation.ui.theme.ColorGraySemiDark
import com.strone.presentation.ui.theme.ColorGraySemiLight
import com.strone.presentation.util.clickable
import com.strone.presentation.util.getUnitPrice
import com.strone.presentation.util.toBigDecimalRemoveComma
import java.math.BigDecimal

@Composable
fun TransactionPriceInputRow(
    modifier: Modifier,
    priceText: String,
    onPriceChange: (String) -> Unit,
) {
    val price = priceText.toBigDecimalRemoveComma()
    val showLabel = priceText.isEmpty()
    val iconTint = if(showLabel) ColorGraySemiLight else Color.Black

    FilledCentralTextField(
        value = priceText,
        onValueChange = onPriceChange,
        modifier = modifier,
        navigationIcon = {
            Icon(painter = painterResource(id = R.drawable.ic_remove),
                contentDescription = stringResource(
                    id = R.string.action_price_minus
                ),
                modifier = Modifier.clickable(showRipple = false) {
                    onPriceChange(
                        (price - price.getUnitPrice()).max(BigDecimal(0)).toPlainString())
                }, tint = iconTint
            )
        },
        trailingIcon = {
            Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = stringResource(
                id = R.string.action_price_plus
            ), modifier = Modifier.clickable(showRipple = false) {
                onPriceChange(
                    (price + price.getUnitPrice()).toPlainString()
                )
            }, tint = iconTint
            )
        },
        label = stringResource(id = R.string.price),
        softWrap = false,
        cursorBrush = SolidColor(Color.Unspecified),
        color = if(showLabel) ColorGraySemiDark else Color.Black,
        fontWeight = FontWeight.Bold,
        singleLine = true,
        fontSize = 16.sp,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
    )
}