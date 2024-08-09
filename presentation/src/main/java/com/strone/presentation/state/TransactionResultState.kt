package com.strone.presentation.state

sealed class TransactionResultState {
    data object Success: TransactionResultState()
    data class Failure(val message: String): TransactionResultState()
}