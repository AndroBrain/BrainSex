package com.androbrain.brainsex.di

import com.androbrain.brainsex.data.datasource.TestLocalDataSource
import com.androbrain.brainsex.data.repository.TestRepository
import com.androbrain.brainsex.data.repository.TestRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideTestRepository(testLocalDataSource: TestLocalDataSource): TestRepository =
        TestRepositoryImpl(testLocalDataSource)
}
