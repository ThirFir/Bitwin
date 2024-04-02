package com.strone.data.constant

object URLConstant {

    object V1 {
        private const val V1 = "/v1"
        object Market {
            private const val MARKET = "$V1/market"
            const val ALL = "$MARKET/all"
        }

        object Candle {
            private const val CANDLES = "$V1/candles"
            const val MINUTES = "$CANDLES/minutes"
            const val DAYS = "$CANDLES/days"
            const val WEEKS = "$CANDLES/weeks"
            const val MONTHS = "$CANDLES/months"
        }

        object Trade {
            private const val TRADES = "$V1/trades"
            const val TICKS = "$TRADES/ticks"
        }

        object Orderbook {
            const val ORDERBOOK = "$V1/orderbook"
        }
    }
}