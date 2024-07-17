package com.strone.presentation.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.Role
import androidx.navigation.NavHostController


internal inline fun Modifier.clickable(
    showRipple: Boolean = true,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    crossinline onClick: ()->Unit
): Modifier = composed {
    if(!showRipple) {
        this.clickable(indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) { onClick() }
    } else {
        this.clickable { onClick() }
    }
}

internal fun NavHostController.navigateToOtherTab(route: String) {
    navigate(route) {
        graph.startDestinationRoute?.let { route ->
            popUpTo(route) { saveState = true }
        }
        launchSingleTop = true
        restoreState = true
    }
}