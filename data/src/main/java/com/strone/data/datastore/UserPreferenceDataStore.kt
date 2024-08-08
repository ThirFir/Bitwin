package com.strone.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.strone.data.entity.UserEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

interface UserPreferenceDataStore {
    fun saveUser(user: UserEntity): Flow<Unit>
    val user: Flow<UserEntity?>
}

class UserPreferenceDataStoreImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : UserPreferenceDataStore {
    override fun saveUser(user: UserEntity): Flow<Unit> {
        return flow {
            context.dataStore.edit {
                it[USER_ID_PREFERENCE] = user.id
                it[USER_NICKNAME_PREFERENCE] = user.nickname
                it[USER_IS_GUEST_PREFERENCE] = user.isGuest
            }
        }
    }

    override val user: Flow<UserEntity?>
        get() = context.dataStore.data.map {
            val id = it[USER_ID_PREFERENCE]
            val nickname = it[USER_NICKNAME_PREFERENCE]
            val isGuest = it[USER_IS_GUEST_PREFERENCE]
            if (id != null && nickname != null && isGuest != null) {
                UserEntity(id, nickname, isGuest)
            } else {
                null
            }
        }

    companion object {
        private val USER_ID_PREFERENCE = stringPreferencesKey("user_id")
        private val USER_NICKNAME_PREFERENCE = stringPreferencesKey("user_nickname")
        private val USER_IS_GUEST_PREFERENCE = booleanPreferencesKey("user_is_guest")
    }
}