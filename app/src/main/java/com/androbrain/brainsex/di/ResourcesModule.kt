package com.androbrain.brainsex.di

import android.content.Context
import com.androbrain.brainsex.core.QuestionWithAnswers
import com.androbrain.brainsex.core.gender.Gender
import com.androbrain.brainsex.data.TestDataCreator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ResourcesModule {

    @Provides
    fun provideBrainSexQuestions(
        @ApplicationContext context: Context
    ): List<QuestionWithAnswers<Gender>> =
        TestDataCreator.getBrainSexQuestions(context.resources)

}