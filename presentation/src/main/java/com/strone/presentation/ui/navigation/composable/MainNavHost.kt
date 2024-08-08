package com.strone.presentation.ui.navigation.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.strone.presentation.model.TickerModel
import com.strone.presentation.ui.cryptoList.composable.CryptoListScreen
import com.strone.presentation.ui.home.composable.HomeScreen
import com.strone.presentation.ui.navigation.item.Routes
import com.strone.presentation.ui.portfolio.composable.PortfolioScreen
import com.strone.presentation.ui.ranking.composable.RankingScreen

@Composable
fun MainNavHost(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String,
    searchInput: String,
    hotTickers: List<TickerModel>
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
                hotTickers = hotTickers,
            )
        }
        composable(Routes.CRYPTO_LIST) {
            CryptoListScreen(
                modifier = Modifier.fillMaxSize(),
                searchInput = searchInput,
            )
        }
        composable(Routes.RANKING) {
            RankingScreen()
        }
        composable(Routes.PORTFOLIO) {
            PortfolioScreen()
        }
    }
}