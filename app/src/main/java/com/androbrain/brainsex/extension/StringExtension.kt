package com.androbrain.brainsex.extension

import java.util.*

fun String.capitalizeSentence(): String = replaceFirstChar {
    if (it.isLowerCase())
        it.titlecase(Locale.ROOT)
    else
        it.toString()
}
