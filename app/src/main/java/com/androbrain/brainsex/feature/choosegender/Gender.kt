package com.androbrain.brainsex.feature.choosegender

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Gender : Parcelable {
    MALE, FEMALE, UNKNOWN;
}