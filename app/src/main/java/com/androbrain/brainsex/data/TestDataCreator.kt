package com.androbrain.brainsex.data

import android.content.res.Resources
import com.androbrain.brainsex.R
import com.androbrain.brainsex.core.Answer
import com.androbrain.brainsex.core.QuestionWithAnswers
import com.androbrain.brainsex.core.gender.Gender
import com.androbrain.brainsex.core.gender.GenderAPoints
import com.androbrain.brainsex.core.gender.GenderBPoints
import com.androbrain.brainsex.core.gender.GenderCPoints
import com.androbrain.brainsex.extension.capitalizeSentence

private const val ANSWER_WITH_QUESTIONS_SIZE = 4

class TestDataCreator {
    companion object {
        fun getBrainSexQuestions(resources: Resources): List<QuestionWithAnswers<Gender>> =
            resources.getStringArray(R.array.brain_sex_questions)
                .toList()
                .windowed(size = ANSWER_WITH_QUESTIONS_SIZE, step = ANSWER_WITH_QUESTIONS_SIZE)
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
}