package com.androbrain.brainsex.di

import android.content.Context
import com.androbrain.brainsex.data.TestDataCreator
import com.androbrain.brainsex.model.QuestionWithAnswers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MaleTest

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FemaleTest

@Module
@InstallIn(SingletonComponent::class)
object ResourcesModule {

    @Provides
    @MaleTest
    fun provideMaleBrainSexQuestions(
        @ApplicationContext context: Context
    ): List<QuestionWithAnswers> =
        TestDataCreator.getBrainSexQuestions(context.resources, true)

    @Provides
    @FemaleTest
    fun provideFemaleBrainSexQuestions(
        @ApplicationContext context: Context
    ): List<QuestionWithAnswers> =
        TestDataCreator.getBrainSexQuestions(context.resources, false)
}