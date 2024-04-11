package com.strone.data.constant

object URLConstant {

    const val SOCKET_BASE_URL_V1 = "wss://api.upbit.com/websocket/v1/"
    const val BASE_URL = "https://api.upbit.com/"

    object V1 {
        const val V1 = "v1/"
        object Market {
            private const val MARKET = "${V1}market/"
            const val ALL = "${MARKET}all/"
        }

        object Candle {
            private const val CANDLES = "${V1}candles/"
            const val MINUTES = "${CANDLES}minutes/"
            const val DAYS = "${CANDLES}days/"
            const val WEEKS = "${CANDLES}weeks/"
            const val MONTHS = "${CANDLES}months/"
        }

        object Trade {
            private const val TRADES = "${V1}trades/"
            const val TICKS = "${TRADES}ticks/"
        }

        object Orderbook {
            const val ORDERBOOK = "${V1}orderbook/"
        }
    }
}