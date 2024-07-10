package com.strone.presentation.ui.navigation.item

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.strone.presentation.R

enum class MainBottomNavItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String
) {
    Home(
        title = R.string.title_home,
        icon = R.drawable.ic_home,
        route = Routes.HOME
    ),

    CryptoList(
        title = R.string.title_crypto_list,
        icon = R.drawable.ic_crypto_list,
        route = Routes.CRYPTO_LIST
    ),

    Ranking(
        title = R.string.title_ranking,
        icon = R.drawable.ic_ranking,
        route = Routes.RANKING
    ),

    Portfolio(
        title = R.string.title_portfolio,
        icon = R.drawable.ic_portfolio,
        route = Routes.PORTFOLIO
    )
}