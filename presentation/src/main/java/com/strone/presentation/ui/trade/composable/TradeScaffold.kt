package com.strone.presentation.ui.trade.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.strone.core.state.UiState
import com.strone.presentation.model.TickerModel
import com.strone.presentation.ui.loading.LoadingScreen
import com.strone.presentation.ui.navigation.composable.TradeNavHost
import com.strone.presentation.ui.navigation.item.Routes
import com.strone.presentation.ui.navigation.item.TradeNavItem
import com.strone.presentation.ui.theme.ColorGray
import com.strone.presentation.ui.theme.ColorGrayLight
import com.strone.presentation.ui.topbar.trade.TradeTopAppBar
import com.strone.presentation.ui.trade.viewmodel.TradeViewModel
import com.strone.presentation.util.findActivity
import com.strone.presentation.util.navigateToOtherTab

@Composable
fun TradeScaffold(
    modifier: Modifier,
    navController: NavHostController,
    tickerSnapshot: TickerModel,
    viewModel: TradeViewModel = hiltViewModel<TradeViewModel, TradeViewModel.Factory> { it.create(tickerSnapshot) }
) {

    val ticker by viewModel.ticker.collectAsStateWithLifecycle()
    val orderbook by viewModel.orderbook.collectAsStateWithLifecycle()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val startDestination = Routes.TRANSACTION
    var currentRoute by remember { mutableStateOf(Routes.TRANSACTION) }
    var destinationRoute by remember { mutableStateOf(Routes.TRANSACTION) }

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val context = LocalContext.current

    if (uiState is UiState.Loading) {
        LoadingScreen()
    } else {
        orderbook?.let { ob ->
            Scaffold(
                topBar = {
                    TradeTopAppBar(modifier = Modifier.padding(horizontal = 24.dp), ticker = ticker, onNavigationIconClicked = {
                        context.findActivity().finish()
                    })
                },
            ) {
                Surface(modifier = modifier.padding(horizontal = 24.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        TradeTickerRow(modifier = Modifier, ticker = ticker)
                        TabRow(
                            modifier = Modifier,
                            selectedTabIndex = selectedTabIndex,
                            indicator = { tabPositions ->
                                if (selectedTabIndex < tabPositions.size) {
                                    TabRowDefaults.SecondaryIndicator(
                                        Modifier.tabIndicatorOffset(
                                            tabPositions[selectedTabIndex]
                                        ),
                                        color = Color.Black
                                    )
                                }
                            },
                            divider = {
                                HorizontalDivider(color = ColorGrayLight, thickness = 0.5.dp)
                            },
                            tabs = {
                                TradeNavItem.entries.forEach {
                                    Tab(
                                        selected = currentRoute == it.route, onClick = {
                                            currentRoute = it.route
                                        }, selectedContentColor = Color.Black,
                                        unselectedContentColor = ColorGray
                                    ) {
                                        Text(
                                            text = stringResource(it.title),
                                            fontSize = 13.sp,
                                            letterSpacing = 0.2.sp,
                                            modifier = Modifier.padding(vertical = 8.dp)
                                        )
                                    }
                                }
                            }
                        )
                        TradeNavHost(
                            modifier = Modifier,
                            navController = navController,
                            startDestination = startDestination,
                            ticker = ticker,
                            orderbook = ob
                        )
                    }
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
            selectedTabIndex = TradeNavItem.entries.indexOfFirst { it.route == currentRoute }
        }
    }
}
