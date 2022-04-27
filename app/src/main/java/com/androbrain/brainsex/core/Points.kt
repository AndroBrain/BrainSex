package com.androbrain.brainsex.core

import android.os.Parcelable

interface Points<T> : Parcelable {
    fun getPointsByFilter(filter: T): Int
}
