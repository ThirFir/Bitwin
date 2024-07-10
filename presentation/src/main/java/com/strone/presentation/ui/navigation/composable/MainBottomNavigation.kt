package com.strone.presentation.ui.navigation.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.strone.presentation.ui.navigation.item.MainBottomNavItem
import com.strone.presentation.util.ClearRippleTheme

@Composable
fun MainBottomNavigation(
    modifier: Modifier = Modifier,
    containerColor: Color,
    contentColor: Color,
    navController: NavHostController,
    onItemClick: (String) -> Unit,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val items = MainBottomNavItem.entries

    AnimatedVisibility(visible = items.map {
        it.route
    }.contains(currentRoute)) {
        CompositionLocalProvider(
            LocalRippleTheme provides ClearRippleTheme
        ) {
            NavigationBar(
                modifier = modifier,
                containerColor = containerColor,
            ) {
                items.forEach {
                    NavigationBarItem(
                        selected = currentRoute == it.route,
                        onClick = { onItemClick(it.route) },
                        colors = NavigationBarItemDefaults.colors().copy(
                            selectedIndicatorColor = Color.Transparent,
                            selectedIconColor = contentColor,
                            selectedTextColor = contentColor,
                        ),
                        icon = {
                            Icon(
                                painter = painterResource(id = it.icon),
                                contentDescription = stringResource(id = it.title),
                            )
                        })
                }
            }
        }
    }
}