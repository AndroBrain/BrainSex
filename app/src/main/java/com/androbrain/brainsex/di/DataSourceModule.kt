package com.androbrain.brainsex.di

import android.content.Context
import com.androbrain.brainsex.data.TestLocalDataSource
import com.androbrain.brainsex.data.TestLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideTestLocalDataSource(
        @ApplicationContext context: Context
    ): TestLocalDataSource = TestLocalDataSourceImpl(context)
}
