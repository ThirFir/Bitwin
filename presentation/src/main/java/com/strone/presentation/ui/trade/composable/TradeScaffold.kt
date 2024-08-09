package com.strone.presentation.ui.trade.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import com.strone.presentation.R
import com.strone.presentation.model.TickerModel
import com.strone.presentation.state.TransactionResultState
import com.strone.presentation.state.UiState
import com.strone.presentation.ui.LocalAssetComposition
import com.strone.presentation.ui.LocalMarketComposition
import com.strone.presentation.ui.LocalOnBuyComposition
import com.strone.presentation.ui.LocalOnSellComposition
import com.strone.presentation.ui.LocalTickerComposition
import com.strone.presentation.ui.loading.LoadingScreen
import com.strone.presentation.ui.navigation.composable.TradeNavHost
import com.strone.presentation.ui.navigation.item.Routes
import com.strone.presentation.ui.navigation.item.TradeNavItem
import com.strone.presentation.ui.theme.ColorGray
import com.strone.presentation.ui.theme.ColorGrayLight
import com.strone.presentation.ui.topbar.trade.TradeTopAppBar
import com.strone.presentation.ui.trade.viewmodel.TradeViewModel
import com.strone.presentation.ui.trade.viewmodel.TransactionViewModel
import com.strone.presentation.util.finishActivity
import com.strone.presentation.util.navigateToOtherTab
import kotlinx.coroutines.launch

@Composable
fun TradeScaffold(
    modifier: Modifier,
    navController: NavHostController,
    tickerSnapshot: TickerModel,
    tradeViewModel: TradeViewModel = hiltViewModel<TradeViewModel, TradeViewModel.Factory> { it.create(tickerSnapshot) },
    transactionViewModel: TransactionViewModel = hiltViewModel()
) {

    val ticker by tradeViewModel.ticker.collectAsStateWithLifecycle()
    val orderbook by tradeViewModel.orderbook.collectAsStateWithLifecycle()
    val markets by tradeViewModel.markets.collectAsStateWithLifecycle()
    val asset by transactionViewModel.asset.collectAsStateWithLifecycle()

    val uiState by tradeViewModel.uiState.collectAsStateWithLifecycle()
    val transactionState = transactionViewModel.transactionResultState

    val startDestination = Routes.TRANSACTION
    var currentRoute by remember { mutableStateOf(Routes.TRANSACTION) }
    var destinationRoute by remember { mutableStateOf(Routes.TRANSACTION) }

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val lifecycleOwner = LocalLifecycleOwner.current

    if (uiState is UiState.Loading) {
        LoadingScreen()
    } else {
        orderbook?.let { ob ->
            CompositionLocalProvider(LocalMarketComposition provides markets, LocalTickerComposition provides ticker,
                LocalOnBuyComposition provides { transactionViewModel.buy(it) }, LocalOnSellComposition provides { transactionViewModel.sell(it) },
                LocalAssetComposition provides asset) {
                Scaffold(
                    topBar = {
                        TradeTopAppBar(
                            modifier = Modifier.padding(horizontal = 24.dp),
                            onNavigationIconClicked = {
                                context.finishActivity()
                            })
                    }, snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    }
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
                                orderbook = ob
                            )
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(uiState) {
        if (uiState is UiState.Initial) {
            tradeViewModel.fetchTicker(ticker.code)
            tradeViewModel.fetchOrderBook(ticker.code)
        }
    }

    LaunchedEffect(currentRoute) {
        if (currentRoute != destinationRoute) {
            navController.navigateToOtherTab(currentRoute)
            destinationRoute = currentRoute
            selectedTabIndex = TradeNavItem.entries.indexOfFirst { it.route == currentRoute }
        }
    }

    val successMessage = stringResource(id = R.string.success_transaction)
    val actionLabel = stringResource(id = R.string.close)
    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            transactionState.collect { result ->
                snackbarHostState.currentSnackbarData?.dismiss()
                if (result is TransactionResultState.Success)
                    snackbarHostState.showSnackbar(successMessage)
                else snackbarHostState.showSnackbar(
                    message = (result as TransactionResultState.Failure).message,
                    actionLabel = actionLabel,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }
}
