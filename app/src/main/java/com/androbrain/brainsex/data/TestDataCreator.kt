package com.androbrain.brainsex.data

import android.content.res.Resources
import com.androbrain.brainsex.R
import com.androbrain.brainsex.model.Answer
import com.androbrain.brainsex.model.QuestionWithAnswers

private const val ANSWER_WITH_QUESTIONS_SIZE = 4

private const val A_POINTS = -5
private const val B_POINTS = 5
private const val C_MALE_POINTS = 15
private const val C_FEMALE_POINTS = 10

class TestDataCreator {
    companion object {
        //        TODO figure out a better way to create these questions
        fun getBrainSexQuestions(resources: Resources, isMale: Boolean): List<QuestionWithAnswers> =
            resources.getStringArray(R.array.brain_sex_questions)
                .toList()
                .windowed(size = ANSWER_WITH_QUESTIONS_SIZE, step = ANSWER_WITH_QUESTIONS_SIZE)
                .map { questionWithAnswers ->
                    QuestionWithAnswers(
                        question = questionWithAnswers.first(),
                        answers = listOf(
                            Answer(questionWithAnswers[1], A_POINTS),
                            Answer(questionWithAnswers[2], B_POINTS),
                            Answer(
                                questionWithAnswers[3],
                                if (isMale) C_MALE_POINTS else C_FEMALE_POINTS
                            ),
                        )
                    )
                }
    }
}