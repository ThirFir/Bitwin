package com.strone.presentation.state.compose

import androidx.annotation.StringRes
import com.strone.presentation.R

enum class CryptoTabState(
    @StringRes val title: Int
) {
    ALL(R.string.tab_crypto_all),
    HOT(R.string.tab_crypto_hot),
    FAVORITES(R.string.tab_crypto_favorites)
}