package com.strone.presentation.ui.trade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.strone.domain.constants.CryptoConstants.TICKER
import com.strone.presentation.model.TickerModel
import com.strone.presentation.ui.theme.BitwinTheme
import com.strone.presentation.ui.trade.composable.TradeScaffold
import com.strone.presentation.util.getParcelableExtraCompat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TradeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val ticker = intent.getParcelableExtraCompat<TickerModel>(TICKER)
        if (ticker == null) {
            finish()
            return  // TODO : Error 처리
        }
        setContent {
            BitwinTheme {
                val navController = rememberNavController()
                TradeScaffold(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    tickerSnapshot = ticker
                )
            }
        }
    }
}
