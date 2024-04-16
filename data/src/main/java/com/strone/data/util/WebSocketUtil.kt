package com.strone.data.util

import com.squareup.moshi.JsonWriter
import com.strone.data.constant.Constant.CODES
import com.strone.data.constant.Constant.DEFAULT
import com.strone.data.constant.Constant.FORMAT
import com.strone.data.constant.Constant.TICKER
import com.strone.data.constant.Constant.TICKET
import com.strone.data.constant.Constant.TYPE
import okio.Buffer
import java.util.UUID

/**
 * For Upbit WebSocket
 */
internal fun List<String?>.getSendJson(): String {
    val uuid = UUID.randomUUID().toString()

    val buffer = Buffer()
    val writer = JsonWriter.of(buffer)

    writer.beginArray()

    writer.beginObject()
    writer.name(TICKET).value(uuid)
    writer.endObject()

    writer.beginObject()
    writer.name(TYPE).value(TICKER)
    writer.name(CODES).beginArray()
    this.forEach {
        writer.value(it)
    }
    writer.endArray()
    writer.endObject()

    writer.beginObject()
    writer.name(FORMAT).value(DEFAULT)
    writer.endObject()

    writer.endArray()
    return buffer.readUtf8()
}