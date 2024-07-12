package com.strone.presentation

import androidx.annotation.StringRes

enum class CryptoSortState(
    @StringRes val sortDescription: Int
) {
    NAME_DESCENDING(R.string.sort_by_name_descending),
    NAME_ASCENDING(R.string.sort_by_name_ascending),
    PRICE_DESCENDING(R.string.sort_by_price_descending),
    PRICE_ASCENDING(R.string.sort_by_price_ascending),
    CHANGE_RATE_DESCENDING(R.string.sort_by_change_rate_descending),
    CHANGE_RATE_ASCENDING(R.string.sort_by_change_rate_ascending),
    VOLUME_DESCENDING(R.string.sort_by_volume_descending),
    VOLUME_ASCENDING(R.string.sort_by_volume_ascending),
}