package com.strone.data.util

import com.strone.data.constant.URLConstant.IMAGE_BASE_URL

internal fun String.toSignature(): String {
    return this.split("-")[1]
}

internal fun String.getImageUrl(): String {
    return "${IMAGE_BASE_URL}${toSignature()}.png"
}