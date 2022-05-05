package com.androbrain.brainsex.feature.result

import androidx.annotation.StringRes
import com.androbrain.brainsex.R

data class ResultState(
    @StringRes val title: Int,
    @StringRes val description: Int,
) {
    companion object {
        val Initial
            get() = ResultState(
                title = R.string.empty,
                description = R.string.empty
            )
    }
}
