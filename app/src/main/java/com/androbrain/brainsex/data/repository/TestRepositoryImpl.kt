package com.androbrain.brainsex.data.repository

import com.androbrain.brainsex.data.datasource.TestLocalDataSource

class TestRepositoryImpl(private val testLocalDataSource: TestLocalDataSource) : TestRepository {
    override fun getGenderTest() = testLocalDataSource.getGenderTest()
}
