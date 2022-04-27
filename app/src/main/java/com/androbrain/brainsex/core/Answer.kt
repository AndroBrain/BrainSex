package com.androbrain.brainsex.core

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Answer<T>(
    val text: String,
    val points: Points<T>
) : Parcelable
