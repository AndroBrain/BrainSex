package com.androbrain.brainsex.data

import android.content.res.Resources
import com.androbrain.brainsex.R
import com.androbrain.brainsex.extension.capitalizeSentence
import com.androbrain.brainsex.core.Answer
import com.androbrain.brainsex.core.QuestionWithAnswers

private const val ANSWER_WITH_QUESTIONS_SIZE = 4

class TestDataCreator {
    companion object {
        fun getBrainSexQuestions(resources: Resources): List<QuestionWithAnswers> =
            resources.getStringArray(R.array.brain_sex_questions)
                .toList()
                .windowed(size = ANSWER_WITH_QUESTIONS_SIZE, step = ANSWER_WITH_QUESTIONS_SIZE)
                .map { questionWithAnswers ->
                    QuestionWithAnswers(
                        question = questionWithAnswers.first(),
                        answers = listOf(
                            Answer(questionWithAnswers[1].capitalizeSentence(), 5),
                            Answer(questionWithAnswers[2].capitalizeSentence(), 10),
                            Answer(questionWithAnswers[3].capitalizeSentence(), 15),
                        )
                    )
                }
    }
}