package com.strone.presentation.ui.trade.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.strone.presentation.ui.navigation.composable.TradeNavHost
import com.strone.presentation.ui.navigation.item.Routes
import com.strone.presentation.ui.navigation.item.TradeNavItem
import com.strone.presentation.ui.topbar.trade.TradeTopAppBar
import com.strone.presentation.ui.trade.TradeActivity
import com.strone.presentation.ui.trade.viewmodel.TradeViewModel
import com.strone.presentation.util.findActivity
import com.strone.presentation.util.navigateToOtherTab

@Composable
fun TradeScaffold(
    modifier: Modifier,
    navController: NavHostController,
    code: String,
    viewModel: TradeViewModel = hiltViewModel()
) {

    val ticker by viewModel.ticker.collectAsStateWithLifecycle()

    val loading by viewModel.isLoading.collectAsStateWithLifecycle()

    val startDestination = Routes.TRANSACTION
    var currentRoute by remember { mutableStateOf(Routes.TRANSACTION) }

    val context = LocalContext.current

    if (loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
            )
        }
    } else {
        ticker?.let { t ->
            Scaffold(
                topBar = {
                    TradeTopAppBar(modifier = Modifier, ticker = t) {
                        //navController.popBackStack()
                        context.findActivity().finish()
                    }
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
                            ticker = t
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(ticker) {
        viewModel.isLoading.emit(ticker == null)
    }

    LaunchedEffect(loading) {
        viewModel.fetchTicker(code)
    }

    LaunchedEffect(currentRoute) {
        //navController.navigateToOtherTab(currentRoute)
    }
}
