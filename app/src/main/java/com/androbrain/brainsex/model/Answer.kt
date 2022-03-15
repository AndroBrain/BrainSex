package com.androbrain.brainsex.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class Answer(
    val text: String,
) : Parcelable
