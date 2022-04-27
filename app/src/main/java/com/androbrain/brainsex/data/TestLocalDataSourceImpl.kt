package com.androbrain.brainsex.data

import android.content.Context
import com.androbrain.brainsex.R
import com.androbrain.brainsex.core.Answer
import com.androbrain.brainsex.core.QuestionWithAnswers
import com.androbrain.brainsex.core.gender.GenderAPoints
import com.androbrain.brainsex.core.gender.GenderBPoints
import com.androbrain.brainsex.core.gender.GenderCPoints
import com.androbrain.brainsex.extension.capitalizeSentence

private const val TEST_ANSWER_WITH_QUESTIONS_SIZE = 4

class TestLocalDataSourceImpl(private val context: Context) : TestLocalDataSource {
    override fun getGenderTest() = context.resources.getStringArray(R.array.brain_sex_questions)
        .toList()
        .windowed(
            size = TEST_ANSWER_WITH_QUESTIONS_SIZE,
            step = TEST_ANSWER_WITH_QUESTIONS_SIZE
        )
        .map { questionWithAnswers ->
            QuestionWithAnswers(
                question = questionWithAnswers.first(),
                answers = listOf(
                    Answer(questionWithAnswers[1].capitalizeSentence(), GenderAPoints),
                    Answer(questionWithAnswers[2].capitalizeSentence(), GenderBPoints),
                    Answer(questionWithAnswers[3].capitalizeSentence(), GenderCPoints),
                )
            )
        }
}
