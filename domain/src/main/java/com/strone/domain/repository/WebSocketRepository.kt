package com.strone.domain.repository

import com.strone.domain.model.StreamingModel
import kotlinx.coroutines.flow.Flow

interface WebSocketRepository<T : StreamingModel> {
    suspend fun fetchStreamingResponse(codes: List<String>) : Flow<T>
    suspend fun fetchStreamingResponse(code: String) : Flow<T> {
        return fetchStreamingResponse(listOf(code))
    }
}