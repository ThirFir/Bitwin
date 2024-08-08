package com.strone.presentation.ui.navigation.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.strone.presentation.model.OrderbookModel
import com.strone.presentation.ui.navigation.item.Routes
import com.strone.presentation.ui.trade.composable.transaction.TransactionScreen

@Composable
fun TradeNavHost(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String,
    orderbook: OrderbookModel
) {

    val orderbookUnits = orderbook.orderbookUnitModels

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.TRANSACTION) {
            TransactionScreen(
                modifier = Modifier.padding(top = 12.dp),
                orderbookUnits = orderbookUnits
            )
        }

        composable(Routes.ORDERBOOK) {

        }

        composable(Routes.CHART) {

        }

        composable(Routes.INFORMATION) {

        }
    }
}