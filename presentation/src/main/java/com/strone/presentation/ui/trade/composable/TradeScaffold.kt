package com.strone.presentation.ui.trade.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.strone.core.state.UiState
import com.strone.domain.model.Ticker
import com.strone.presentation.ui.loading.LoadingScreen
import com.strone.presentation.ui.navigation.composable.TradeNavHost
import com.strone.presentation.ui.navigation.item.Routes
import com.strone.presentation.ui.navigation.item.TradeNavItem
import com.strone.presentation.ui.topbar.trade.TradeTopAppBar
import com.strone.presentation.ui.trade.viewmodel.TradeViewModel
import com.strone.presentation.util.findActivity
import com.strone.presentation.util.navigateToOtherTab

@Composable
fun TradeScaffold(
    modifier: Modifier,
    navController: NavHostController,
    tickerSnapshot: Ticker,
    viewModel: TradeViewModel = hiltViewModel<TradeViewModel, TradeViewModel.Factory> { it.create(tickerSnapshot) }
) {

    val ticker by viewModel.ticker.collectAsStateWithLifecycle()
    val orderbook by viewModel.orderbook.collectAsStateWithLifecycle()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val startDestination = Routes.TRANSACTION
    var currentRoute by remember { mutableStateOf(Routes.TRANSACTION) }
    var destinationRoute by remember { mutableStateOf(Routes.TRANSACTION) }

    val context = LocalContext.current

    if (uiState is UiState.Loading) {
        LoadingScreen()
    } else {
        Scaffold(
            topBar = {
                TradeTopAppBar(modifier = Modifier, ticker = ticker, onNavigationIconClicked = {
                    context.findActivity().finish()
                })
            },
        ) {
            Surface(modifier = modifier) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    TabRow(selectedTabIndex = TradeNavItem.entries.indexOfFirst { it.route == currentRoute }) {
                        TradeNavItem.entries.forEach {
                            Tab(selected = currentRoute == it.route, onClick = {
                                currentRoute = it.route
                            }) {
                                Text(text = stringResource(it.title))
                            }
                        }

                    }
                    TradeNavHost(
                        modifier = Modifier,
                        navController = navController,
                        startDestination = startDestination,
                        ticker = ticker
                    )
                }

            }
        }
    }

    LaunchedEffect(uiState) {
        if (uiState is UiState.Initial) {
            viewModel.fetchTicker(ticker.code)
            viewModel.fetchOrderBook(ticker.code)
        }
    }

    LaunchedEffect(currentRoute) {
        if (currentRoute != destinationRoute) {
            navController.navigateToOtherTab(currentRoute)
            destinationRoute = currentRoute
        }
    }
}
