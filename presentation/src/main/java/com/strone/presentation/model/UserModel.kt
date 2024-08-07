package com.strone.presentation.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class UserModel(
    val id: String,
    val nickname: String,
) : Parcelable
