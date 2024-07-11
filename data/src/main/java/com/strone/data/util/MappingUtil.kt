package com.strone.data.util

import com.strone.data.constant.Constant
import com.strone.data.constant.Constant.EVEN
import com.strone.data.constant.Constant.FALL
import com.strone.data.constant.Constant.RISE
import com.strone.data.constant.URLConstant.IMAGE_BASE_URL
import com.strone.domain.model.ChangeType

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