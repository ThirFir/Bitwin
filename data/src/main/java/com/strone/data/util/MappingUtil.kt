package com.strone.data.util

import com.strone.data.constant.URLConstant.IMAGE_BASE_URL
import com.strone.domain.constants.CryptoConstants.EVEN
import com.strone.domain.constants.CryptoConstants.FALL
import com.strone.domain.constants.CryptoConstants.RISE
import com.strone.domain.model.type.ChangeType

internal fun String.toSignature(): String {
    return this.split("-")[1]
}

internal fun String.getImageUrl(): String {
    return "${IMAGE_BASE_URL}${toSignature()}.png"
}

internal fun String?.toChangeType(): ChangeType {
    return when (this) {
        RISE -> ChangeType.RISE
        EVEN -> ChangeType.EVEN
        FALL -> ChangeType.FALL
        else -> ChangeType.EVEN
    }
}