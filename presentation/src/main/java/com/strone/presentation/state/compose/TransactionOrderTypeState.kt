package com.strone.presentation.state.compose

import androidx.annotation.StringRes
import com.strone.presentation.R

enum class TransactionOrderTypeState(
    @StringRes val title: Int,
) {
    MARKET_ORDER(title = R.string.market_order),
    LIMIT_ORDER(title = R.string.limit_order),
    RESERVATION_ORDER(title = R.string.reservation_order),
}