package com.androbrain.brainsex.data

class TestRepositoryImpl(private val testLocalDataSource: TestLocalDataSource) : TestRepository {
    override fun getGenderTest() = testLocalDataSource.getGenderTest()
}
