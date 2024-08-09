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
import com.strone.domain.constants.CryptoConstants.MAX_TRANSACTION_AMOUNT
import com.strone.presentation.R
import com.strone.presentation.ui.component.textfield.FilledCentralTextField
import com.strone.presentation.ui.theme.ColorGraySemiDark
import com.strone.presentation.ui.theme.ColorGraySemiLight
import com.strone.presentation.util.clickable
import com.strone.presentation.util.getUnitAmount
import com.strone.presentation.util.toBigDecimalRemoveComma
import java.math.BigDecimal

@Composable
fun TransactionVolumeInputRow(
    modifier: Modifier,
    amount: String,
    signature: String,
    onAmountChange: (String) -> Unit,
    transactionPrice: BigDecimal
) {
    val showLabel = amount.isEmpty()
    val iconTint = if(amount.isEmpty()) ColorGraySemiLight else Color.Black

    FilledCentralTextField(
        value = if(showLabel) "" else amount,
        onValueChange = onAmountChange,
        modifier = modifier,
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_remove),
                contentDescription = stringResource(
                    id = R.string.action_price_minus
                ), modifier = Modifier.clickable(showRipple = false) {
                    if (showLabel.not())
                        onAmountChange((amount.toBigDecimalRemoveComma() - transactionPrice.getUnitAmount()).max(BigDecimal(0)).toPlainString())
                }, tint = iconTint
            )
        },
        trailingIcon = {
            Icon(painter = painterResource(id = R.drawable.ic_add),
                contentDescription = stringResource(
                    id = R.string.action_price_plus
                ), modifier = Modifier.clickable(showRipple = false) {
                    if(showLabel.not())
                        onAmountChange((amount.toBigDecimalRemoveComma() + transactionPrice.getUnitAmount()).min(BigDecimal(MAX_TRANSACTION_AMOUNT)).toPlainString())
                }, tint = iconTint
            )
        },
        label = stringResource(id = R.string.amount) + "($signature)",
        color = if(showLabel) ColorGraySemiDark else Color.Black,
        softWrap = false,
        cursorBrush = SolidColor(Color.Unspecified),
        fontWeight = FontWeight.Bold,
        singleLine = true,
        fontSize = 16.sp,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
    )
}