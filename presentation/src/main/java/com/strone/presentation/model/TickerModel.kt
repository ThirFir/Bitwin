package com.strone.presentation.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.strone.domain.model.type.ChangeType
import com.strone.domain.model.type.MarketType
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
@Immutable
data class TickerModel(
    val code: String,   // 마켓 코드 ex) KRW-BTC   웹소켓 전용
    val signature: String, // 코인 시그니처 ex) BTC, ETH, XRP
    val type: MarketType, // 마켓 구분 코드 KRW(원화), BTC(비트코인), USDT(테더)
    val openingPrice: BigDecimal,  // 시가
    val highPrice: BigDecimal,     // 고가
    val lowPrice: BigDecimal,      // 저가
    val tradePrice: BigDecimal,  // 현재가
    val prevClosingPrice: BigDecimal, // 전일 종가
    val change: ChangeType, // 전일 대비 RISE : 상승 EVEN : 보합 FALL : 하락
    val changePrice: BigDecimal, // 부호 없는 전일 대비 값
    val signedChangePrice: BigDecimal, // 전일 대비 값
    val changeRate: BigDecimal, // 부호 없는 전일 대비 등락율
    val signedChangeRate: BigDecimal, // 전일 대비 등락율
    val tradeVolume: BigDecimal, // 가장 최근 거래량
    val accTradeVolume: BigDecimal, // 누적 거래량 (UTC 0시 기준)
    val accTradeVolume24h: BigDecimal, // 24시간 누적 거래량
    val accTradePrice: BigDecimal, // 누적 거래대금 (UTC 0시 기준)
    val accTradePrice24h: BigDecimal, // 24시간 누적 거래대금
    val tradeTime: String, // 최근 거래 시각(UTC) - HHmmss
    val tradeTimestamp: Long, // 최근 거래 타임스탬프
    val tradeDate: String, // 최근 거래 일자(UTC) - yyyyMMdd
    val highest52WeekPrice: BigDecimal, // 52주 최고가
    val highest52WeekDate: String, // 52주 최고가 달성일 yyyy-MM-dd
    val lowest52WeekPrice: BigDecimal, // 52주 최저가
    val lowest52WeekDate: String, // 52주 최저가 달성일 yyyy-MM-dd
    val timestamp: Long, // 타임스탬프
): Parcelable