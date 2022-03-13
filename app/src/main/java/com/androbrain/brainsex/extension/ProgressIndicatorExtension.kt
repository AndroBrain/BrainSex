package com.androbrain.brainsex.extension

import android.os.Build
import com.google.android.material.progressindicator.LinearProgressIndicator

fun LinearProgressIndicator.animateProgressCompat(progress: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        setProgress(progress, true)
    } else {
        setProgress(progress)
    }
}
