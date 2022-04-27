package com.androbrain.brainsex.feature.test

import android.os.Parcelable
import com.androbrain.brainsex.core.QuestionWithAnswers
import com.androbrain.brainsex.feature.choosegender.Gender
import kotlinx.parcelize.Parcelize

@Parcelize
data class TestState(
    val currentQuestionIndex: Int,
    val answerWithQuestions: QuestionWithAnswers?,
    val selectedButtonId: Int?,
    val points: Int,
    val gender: Gender,
) : Parcelable {
    companion object {
        val Initial: TestState
            get() = TestState(
                currentQuestionIndex = 0,
                answerWithQuestions = null,
                selectedButtonId = null,
                points = 0,
                gender = Gender.UNKNOWN,
            )
    }
}