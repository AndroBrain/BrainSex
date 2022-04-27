package com.androbrain.brainsex.feature.test

import android.os.Parcelable
import com.androbrain.brainsex.core.QuestionWithAnswers
import com.androbrain.brainsex.core.gender.Gender
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenderTestState(
    val currentQuestionIndex: Int,
    val answerWithQuestions: QuestionWithAnswers<Gender>?,
    val selectedButtonId: Int?,
    val points: Int,
    val gender: Gender?,
) : Parcelable {
    companion object {
        val Initial: GenderTestState
            get() = GenderTestState(
                currentQuestionIndex = 0,
                answerWithQuestions = null,
                selectedButtonId = null,
                points = 0,
                gender = null
            )
    }
}