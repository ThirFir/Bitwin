package com.strone.data.repository

import com.strone.data.datasource.local.UserLocalDataSource
import com.strone.data.datasource.remote.UserRemoteDataSource
import com.strone.data.entity.AssetEntity
import com.strone.data.mapper.asFlow
import com.strone.data.mapper.toAsset
import com.strone.data.mapper.toAssetEntity
import com.strone.data.mapper.toUser
import com.strone.data.mapper.toUserEntity
import com.strone.domain.model.Asset
import com.strone.domain.model.User
import com.strone.domain.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override val user: Flow<User> = userLocalDataSource.user
        .flatMapLatest { local ->
            if (local != null) {
                flowOf(local)
            } else userRemoteDataSource.user
    }.map { it?.toUser() ?: throw IllegalStateException("User not found") }

    override fun saveUser(user: User): Flow<Unit> {
        val entity = user.toUserEntity()
        if (user.isGuest.not()) {
            userRemoteDataSource.saveUser(entity)
        }
        return userLocalDataSource.saveUser(entity)
    }

    override fun getAsset(id: String): Flow<Asset> {
        return userLocalDataSource.getAsset(id).map(AssetEntity::toAsset)
    }

    override fun insertAsset(asset: Asset): Flow<Unit> {
        return userLocalDataSource.insertAsset(asset.toAssetEntity()).asFlow()
    }

    override fun updateAsset(asset: Asset): Flow<Unit> {
        return userLocalDataSource.updateAsset(asset.toAssetEntity()).asFlow()
    }

    override fun deleteAsset(asset: Asset): Flow<Unit> {
        return userLocalDataSource.deleteAsset(asset.toAssetEntity()).asFlow()
    }
}
