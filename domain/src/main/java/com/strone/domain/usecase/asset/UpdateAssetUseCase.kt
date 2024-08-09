package com.strone.domain.usecase.asset

import com.strone.domain.constants.CryptoConstants.MIN_TRANSACTION_PRICE
import com.strone.domain.exception.ExceptionHandler
import com.strone.domain.model.Asset
import com.strone.domain.model.CryptoTransaction
import com.strone.domain.qualifier.IoDispatcher
import com.strone.domain.repository.UserRepository
import com.strone.domain.usecase.FlowResultUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.math.BigDecimal
import javax.inject.Inject

class UpdateAssetUseCase @Inject constructor(
    private val userRepository: UserRepository,
    exceptionHandler: ExceptionHandler,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : FlowResultUseCase<Asset, Unit>(exceptionHandler, coroutineDispatcher) {

    override fun execute(params: Asset) = userRepository.updateAsset(params)

    fun buy(transaction: CryptoTransaction, asset: Asset?): Flow<Result<Unit>> {
        if (asset == null)
            return flow { emit(Result.failure(IllegalStateException("자산 정보 불러오기에 실패했어요."))) }

        if (transaction.totalPrice > asset.krw)
            return flow { emit(Result.failure(IllegalArgumentException("보유 원화가 부족해요."))) }

        if (transaction.totalPrice < MIN_TRANSACTION_PRICE)
            return flow { emit(Result.failure(IllegalArgumentException("최소 거래 금액을 맞춰주세요."))) }

        val updatedAsset = asset.copy(
            krw = asset.krw.minus(transaction.totalPrice),
            holdings = asset.holdings.toMutableList().apply {
                indexOfFirst { it.code == transaction.code }.takeIf { it != -1 }?.let {
                    set(it, this[it].copy(
                        price = (this[it].price * this[it].volume + BigDecimal(transaction.totalPrice) * transaction.volume) / (this[it].volume + transaction.volume),
                        volume = this[it].volume + transaction.volume),
                    )
                }
            }
        )

        return this(updatedAsset)
    }

    fun sell(transaction: CryptoTransaction, asset: Asset?): Flow<Result<Unit>> {
        if (asset == null)
            return flow { emit(Result.failure(IllegalStateException("자산 정보 불러오기에 실패했어요."))) }

        val holdingIndex = asset.holdings.indexOfFirst { it.code == transaction.code }
        if (holdingIndex < 0 || (transaction.volume > asset.holdings[holdingIndex].volume))
            return flow { emit(Result.failure(IllegalArgumentException("보유 수량이 부족해요."))) }

        val updatedAsset = asset.copy(
            krw = asset.krw.plus(transaction.totalPrice),
            holdings = asset.holdings.toMutableList().apply {
                holdingIndex.let {
                    set(it, this[it].copy(
                        price = (this[it].price * this[it].volume + BigDecimal(transaction.totalPrice) * transaction.volume) / (this[it].volume + transaction.volume),
                        volume = this[it].volume - transaction.volume)
                    )
                }
            }
        )

        return this(updatedAsset)
    }
}