package com.androbrain.brainsex.data

import com.androbrain.brainsex.core.QuestionWithAnswers
import com.androbrain.brainsex.core.gender.Gender

interface TestLocalDataSource {
    fun getGenderTest(): List<QuestionWithAnswers<Gender>>
}
