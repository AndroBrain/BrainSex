package com.androbrain.brainsex.feature.result

import android.os.Parcelable
import androidx.annotation.StringRes
import com.androbrain.brainsex.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultState(
    @StringRes val title: Int,
    @StringRes val description: Int,
) : Parcelable {
    companion object {
        val Initial
            get() = ResultState(
                title = R.string.empty,
                description = R.string.empty
            )
    }
}
