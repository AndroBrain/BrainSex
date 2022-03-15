package com.androbrain.brainsex.data

import android.content.res.Resources
import com.androbrain.brainsex.R
import com.androbrain.brainsex.model.Answer
import com.androbrain.brainsex.model.QuestionWithAnswers

private const val ANSWER_WITH_QUESTIONS_SIZE = 4

class TestDataCreator {
    companion object {
        //        TODO figure out a better way to create these questions
        fun getBrainSexQuestions(resources: Resources): List<QuestionWithAnswers> =
            resources.getStringArray(R.array.brain_sex_questions)
                .toList()
                .windowed(size = ANSWER_WITH_QUESTIONS_SIZE, step = ANSWER_WITH_QUESTIONS_SIZE)
                .map { questionWithAnswers ->
                    QuestionWithAnswers(
                        question = questionWithAnswers.first(),
                        answers = listOf(
                            Answer(questionWithAnswers[1]),
                            Answer(questionWithAnswers[2]),
                            Answer(questionWithAnswers[3]),
                        )
                    )
                }
    }
}