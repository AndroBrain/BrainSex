package com.androbrain.brainsex.core

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Answer(
    val text: String,
    val points: Int
) : Parcelable
