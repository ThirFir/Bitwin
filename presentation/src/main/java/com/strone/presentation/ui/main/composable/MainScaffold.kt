package com.strone.presentation.ui.main.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.strone.presentation.ui.navigation.composable.MainBottomNavigation
import com.strone.presentation.ui.navigation.composable.MainNavHost
import com.strone.presentation.ui.navigation.item.Routes
import com.strone.presentation.ui.theme.ColorPrimary
import com.strone.presentation.ui.topbar.CryptoListTopAppBar

@Composable
fun MainScaffold(
    modifier: Modifier,
    navController: NavHostController
) {
    var currentRoute by remember { mutableStateOf(Routes.CRYPTO_LIST) }
    Scaffold(
        topBar = {
            when(currentRoute) {
                Routes.HOME -> {

                }
                Routes.CRYPTO_LIST -> {
                    CryptoListTopAppBar(
                        modifier = Modifier
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
                    currentRoute = it
                    navController.navigate(it) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
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
                    startDestination = Routes.CRYPTO_LIST
                )
            }
        }
    }
}