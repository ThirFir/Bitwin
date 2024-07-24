package com.strone.core.state

sealed class UiState {
    data object Initial: UiState()
    data object Success: UiState()
    data class Error(val error: Throwable?): UiState()
    data object Loading: UiState()
}