package com.strone.core.viewmodel

import androidx.lifecycle.ViewModel
import com.strone.core.state.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class CryptoBaseViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState>
        get() = _uiState

    private val inProgressTasks = AtomicInteger(0)
    private val job = Job()

    protected fun CoroutineScope.launchWithUiState(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        ignoreCancellationException: Boolean = true,
        block: suspend CoroutineScope.() -> Unit
    ) = this.launch(
        context + job, start
    ) {
        _uiState.value = UiState.Loading
        inProgressTasks.incrementAndGet()

        block()
    }

    protected fun<T> Result<T>.emitUiState(): Result<T> {
        return if(inProgressTasks.decrementAndGet() == 0) {
            this.onSuccess {
                _uiState.value = UiState.Success
            }.onFailure {
                _uiState.value = UiState.Error(it)
                job.cancel()
            }
        } else {
            this
        }
    }
}