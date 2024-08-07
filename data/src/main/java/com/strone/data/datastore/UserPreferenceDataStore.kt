package com.strone.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.strone.domain.model.User
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

interface UserPreferenceDataStore {
    suspend fun saveUser(user: User): Flow<Unit>
    val user: Flow<User?>
}

class UserPreferenceDataStoreImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : UserPreferenceDataStore {
    override suspend fun saveUser(user: User): Flow<Unit> {
        return flow {
            context.dataStore.edit {
                it[USER_ID_PREFERENCE] = user.id
                it[USER_NICKNAME_PREFERENCE] = user.nickname
            }
        }
    }

    override val user: Flow<User?>
        get() = context.dataStore.data.map {
            val userId = it[USER_ID_PREFERENCE]
            val userNickname = it[USER_NICKNAME_PREFERENCE]
            if (userId != null && userNickname != null) {
                User(userId, userNickname)
            } else {
                null
            }
        }

    companion object {
        private val USER_ID_PREFERENCE = stringPreferencesKey("user_id")
        private val USER_NICKNAME_PREFERENCE = stringPreferencesKey("user_nickname")
    }
}