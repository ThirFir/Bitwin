package com.strone.presentation.ui.navigation.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.strone.presentation.model.OrderbookModel
import com.strone.presentation.model.TickerModel
import com.strone.presentation.ui.navigation.item.Routes
import com.strone.presentation.ui.trade.composable.TransactionScreen

@Composable
fun TradeNavHost(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String,
    ticker: TickerModel,
    orderbook: OrderbookModel
) {

    val orderbookUnits = orderbook.orderbookUnitModels

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.TRANSACTION) {
            TransactionScreen(modifier = Modifier, ticker = ticker, orderbookUnits = orderbookUnits)
        }

        composable(Routes.ORDERBOOK) {

        }

        composable(Routes.CHART) {

        }

        composable(Routes.INFORMATION) {

        }
    }
}