package com.strone.core.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class CryptoBaseViewModel: ViewModel() {

    val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
}