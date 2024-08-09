package com.strone.presentation.ui.trade.viewmodel

import androidx.lifecycle.viewModelScope
import com.strone.domain.usecase.asset.DeleteAssetUseCase
import com.strone.domain.usecase.asset.GetAssetUseCase
import com.strone.domain.usecase.asset.InsertAssetUseCase
import com.strone.domain.usecase.asset.UpdateAssetUseCase
import com.strone.presentation.delegate.CryptoNamespaceDelegate
import com.strone.presentation.delegate.UserDelegate
import com.strone.presentation.mapper.toAsset
import com.strone.presentation.mapper.toAssetModel
import com.strone.presentation.model.AssetModel
import com.strone.presentation.model.CryptoTransactionModel
import com.strone.presentation.state.TransactionResultState
import com.strone.presentation.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    cryptoNamespaceDelegate: CryptoNamespaceDelegate,
    userDelegate: UserDelegate,
    private val getAssetUseCase: GetAssetUseCase,
    private val insertAssetUseCase: InsertAssetUseCase,
    private val updateAssetUseCase: UpdateAssetUseCase,
    private val deleteAssetUseCase: DeleteAssetUseCase
) : BaseViewModel(), CryptoNamespaceDelegate by cryptoNamespaceDelegate, UserDelegate by userDelegate {

    private val _transactionResultState = MutableSharedFlow<TransactionResultState>()
    val transactionResultState: SharedFlow<TransactionResultState> = _transactionResultState

    private val _asset = MutableStateFlow<AssetModel?>(null)
    val asset: StateFlow<AssetModel?> = _asset

    init {
        getAsset()
    }

    fun getAsset() {
        viewModelScope.launchWithUiState {
            getAssetUseCase(user.value.id).collect {
                it.onComplete { asset ->
                    _asset.value = asset.toAssetModel()
                }.onFailure {
                    insertAssetUseCase(AssetModel(user.value.id, 1000000000, 0, listOf()).toAsset()).collect {
                        it.onComplete {  }
                    }
                }
            }
        }
    }

    fun buy(transaction: CryptoTransactionModel) {
        viewModelScope.launchWithUiState {
            updateAssetUseCase.buy(transaction.toCryptoTransaction(), asset.value?.toAsset())
                .collect {
                    it.onSuccess {
                        _transactionResultState.emit(TransactionResultState.Success)
                    }.onFailure { e ->
                        _transactionResultState.emit(TransactionResultState.Failure(e.message ?: "알 수 없는 오류가 발생했어요."))
                    }
                }
        }
    }

    fun sell(transaction: CryptoTransactionModel) {
        viewModelScope.launchWithUiState {
            if (transaction.volume == asset.value!!.holdings.find { it.code == transaction.code }?.volume) {
                deleteAssetUseCase(asset.value!!.id).collect {
                    it.onSuccess {
                        _transactionResultState.emit(TransactionResultState.Success)
                    }.onFailure { e ->
                        _transactionResultState.emit(
                            TransactionResultState.Failure(
                                e.message ?: "알 수 없는 오류가 발생했어요."
                            )
                        )
                    }
                }
            } else
                updateAssetUseCase.sell(transaction.toCryptoTransaction(), asset.value?.toAsset())
                    .collect {
                        it.onSuccess {
                            _transactionResultState.emit(TransactionResultState.Success)
                        }.onFailure { e ->
                            _transactionResultState.emit(
                                TransactionResultState.Failure(
                                    e.message ?: "알 수 없는 오류가 발생했어요."
                                )
                            )
                        }
                    }
        }
    }

}