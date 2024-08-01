package com.strone.presentation.state

import androidx.annotation.StringRes
import com.strone.presentation.R

enum class TransactionTabState(
    @StringRes val title: Int
) {
    BUY(R.string.tab_buy),
    SELL(R.string.tab_sell)
}