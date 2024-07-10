package com.strone.presentation.ui.navigation.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.strone.presentation.ui.cryptoList.composable.CryptoListScreen
import com.strone.presentation.ui.home.composable.HomeScreen
import com.strone.presentation.ui.navigation.item.Routes
import com.strone.presentation.ui.portfolio.composable.PortfolioScreen
import com.strone.presentation.ui.ranking.composable.RankingScreen

@Composable
fun MainNavHost(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.HOME) {
            HomeScreen()
        }
        composable(Routes.CRYPTO_LIST) {
            CryptoListScreen()
        }
        composable(Routes.RANKING) {
            RankingScreen()
        }
        composable(Routes.PORTFOLIO) {
            PortfolioScreen()
        }
    }
}
