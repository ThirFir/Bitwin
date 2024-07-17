package com.strone.presentation.ui.navigation.item

import androidx.annotation.StringRes
import com.strone.presentation.R

enum class TradeNavItem(
    @StringRes val title: Int,
    val route: String
) {
    Transaction(
        title = R.string.title_transaction,
        route = Routes.TRANSACTION
    ),

    Orderbook(
        title = R.string.title_orderbook,
        route = Routes.ORDERBOOK
    ),

    Chart(
        title = R.string.title_chart,
        route = Routes.CHART
    ),

    Information(
        title = R.string.title_information,
        route = Routes.INFORMATION
    )
}