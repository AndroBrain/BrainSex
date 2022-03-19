package com.androbrain.brainsex.data

import android.content.res.Resources
import com.androbrain.brainsex.R
import com.androbrain.brainsex.model.Answer
import com.androbrain.brainsex.model.QuestionWithAnswers
import java.util.*

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
                            Answer(questionWithAnswers[1].capitalizeSentence()),
                            Answer(questionWithAnswers[2].capitalizeSentence()),
                            Answer(questionWithAnswers[3].capitalizeSentence()),
                        )
                    )
                }

        private fun String.capitalizeSentence() = replaceFirstChar {
            if (it.isLowerCase())
                it.titlecase(Locale.ROOT)
            else
                it.toString()
        }
    }
}