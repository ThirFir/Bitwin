package com.strone.data.response.rest

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarketResponse(
    @Json(name = "market") val code: String?, // 업비트에서 제공중인 시장 정보
    @Json(name = "korean_name") val koreanName: String?, // 거래 대상 디지털 자산 한글명
    @Json(name = "english_name") val englishName: String?, // 거래 대상 디지털 자산 영문명
    @Json(name = "market_warning") val marketWarning: String?, // 유의 종목 여부 - NONE : 해당 사항 없음, CAUTION : 투자유의
)
