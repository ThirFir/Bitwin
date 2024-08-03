package com.strone.data.response.websocket

data class ErrorStreamingResponse(
    val exception: Throwable
) : UpbitStreamingResponse()