package com.androbrain.brainsex.data.repository

import com.androbrain.brainsex.core.QuestionWithAnswers
import com.androbrain.brainsex.core.gender.Gender

interface TestRepository {
    fun getGenderTest(): List<QuestionWithAnswers<Gender>>
}
