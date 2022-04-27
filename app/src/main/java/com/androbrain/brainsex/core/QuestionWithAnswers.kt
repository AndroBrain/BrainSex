package com.androbrain.brainsex.core

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuestionWithAnswers<T>(
    val question: String,
    val answers: List<Answer<T>>
) : Parcelable
