package com.androbrain.brainsex.extension

import androidx.navigation.NavOptionsBuilder


fun NavOptionsBuilder.addFadeAnimations() {
    anim {
        enter = android.R.anim.fade_in
        exit = android.R.anim.fade_out
        popEnter = android.R.anim.fade_in
        popExit = android.R.anim.fade_out
    }
}
