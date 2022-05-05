package com.androbrain.brainsex.extension

import android.content.Context
import android.content.Intent

fun Context.shareContent(action: Intent.() -> Unit) {
    val sendIntent = Intent()
    sendIntent.action = Intent.ACTION_SEND
    sendIntent.action()
    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}
