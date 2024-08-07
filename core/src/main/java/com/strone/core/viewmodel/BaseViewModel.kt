package com.strone.core.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.strone.core.state.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Initial)
    val uiState: StateFlow<UiState>
        get() = _uiState

    private val inProgressTasks = AtomicInteger(0)
    private val job = Job()
    private val mutex = Mutex()

    protected fun CoroutineScope.launchWithUiState(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = this.launch(
        context + job, start
    ) {
        _uiState.value = UiState.Loading
        inProgressTasks.incrementAndGet()

        block()
    }

    /**
     * Result를 수신 객체로 하여 UiState와 실행 코드를 한 번에 처리
     * @param block 실행 코드
     * @return Result<T>
     */
    protected suspend fun<T> Result<T>.onComplete(block: suspend (T) -> Unit): Result<T> {
        val result = mutex.withLock {
            if (inProgressTasks.decrementAndGet() <= 0) {
                inProgressTasks.set(0)
                this.onSuccess {
                    _uiState.value = UiState.Success
                }
            } else {    // 다른 작업이 진행 중이면 Loading 상태 유지
                this
            }.onFailure {
                _uiState.value = UiState.Error(it)
                Log.d("CryptoBaseViewModel", "Error: $it")
                job.cancel()    // 에러 발생 시 모든 작업 취소
            }
        }
        return result.onSuccess {
            block(it)
        }
    }
}