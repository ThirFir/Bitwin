package com.strone.presentation.ui.main.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.strone.core.state.UiState
import com.strone.presentation.model.MarketModel
import com.strone.presentation.state.CryptoSortState
import com.strone.presentation.ui.cryptoList.viewmodel.TickerViewModel
import com.strone.presentation.ui.loading.LoadingScreen
import com.strone.presentation.ui.navigation.composable.MainBottomNavigation
import com.strone.presentation.ui.navigation.composable.MainNavHost
import com.strone.presentation.ui.navigation.item.Routes
import com.strone.presentation.ui.theme.ColorPrimary
import com.strone.presentation.ui.topbar.main.CryptoListTopAppBar
import com.strone.presentation.ui.topbar.main.HomeTopAppBar
import com.strone.presentation.util.navigateToOtherTab

val LocalMarketComposition = staticCompositionLocalOf<Map<String, MarketModel>> {
    mapOf()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScaffold(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: TickerViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val tickers = viewModel.tickers
    val hotTickers = viewModel.hotTickers
    val markets by viewModel.markets.collectAsStateWithLifecycle()

    val startDestination = Routes.HOME
    var currentRoute by remember { mutableStateOf(Routes.HOME) }
    var destinationRoute by remember { mutableStateOf(Routes.HOME) }
    val searchInputState = rememberTextFieldState()
    var currentCryptoSortState by remember { mutableStateOf(CryptoSortState.CHANGE_RATE_DESCENDING) }

    if (uiState is UiState.Loading) {
        LoadingScreen()
    } else {
        CompositionLocalProvider(LocalMarketComposition provides markets) {
            Scaffold(
                topBar = {
                    when (currentRoute) {
                        Routes.HOME -> {
                            HomeTopAppBar(
                                modifier = Modifier
                            )
                        }

                        Routes.CRYPTO_LIST -> {
                            CryptoListTopAppBar(
                                modifier = Modifier,
                                searchInputState = searchInputState,
                                onSelectSortState = {
                                    currentCryptoSortState = it
                                }
                            )
                        }

                        Routes.RANKING -> {
                            // TopBar for CryptoDetails
                        }

                        Routes.PORTFOLIO -> {
                            // TopBar for CryptoMarket
                        }
                    }
                },
                bottomBar = {
                    MainBottomNavigation(
                        modifier = Modifier,
                        containerColor = Color.Transparent,
                        contentColor = ColorPrimary,
                        navController = navController,
                        onItemClick = {
                            destinationRoute = it
                        }
                    )
                }
            ) {
                Surface(
                    modifier = modifier,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        MainNavHost(
                            modifier = Modifier,
                            navController = navController,
                            startDestination = startDestination,
                            searchInput = searchInputState.text.toString(),
                            tickers = tickers,
                            hotTickers = hotTickers,
                        )
                    }
                }
            }

            LaunchedEffect(currentCryptoSortState) {
                viewModel.sortTickers(currentCryptoSortState)
            }

            LaunchedEffect(destinationRoute) {
                if (currentRoute != destinationRoute) {
                    navController.navigateToOtherTab(destinationRoute)
                    currentRoute = destinationRoute
                }
            }
        }
    }
}