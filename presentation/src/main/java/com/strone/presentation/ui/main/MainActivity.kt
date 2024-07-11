package com.strone.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.strone.presentation.ui.main.composable.MainScaffold
import com.strone.presentation.ui.theme.BitwinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BitwinTheme {
                val navController = rememberNavController()
                MainScaffold(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController
                )
            }
        }
    }
}
