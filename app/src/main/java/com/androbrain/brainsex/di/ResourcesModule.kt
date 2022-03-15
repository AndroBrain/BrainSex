package com.androbrain.brainsex.di

import android.content.Context
import com.androbrain.brainsex.data.TestDataCreator
import com.androbrain.brainsex.model.QuestionWithAnswers
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
    ): List<QuestionWithAnswers> =
        TestDataCreator.getBrainSexQuestions(context.resources)

}