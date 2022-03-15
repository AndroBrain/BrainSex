package com.androbrain.brainsex.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuestionWithAnswers(
    val question: String,
    val answers: List<Answer>
) : Parcelable

