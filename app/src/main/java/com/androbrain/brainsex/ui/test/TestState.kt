package com.androbrain.brainsex.ui.test

data class TestState(
    val currentQuestionIndex: Int
) {
    companion object {
        val Initial
            get() = TestState(
                currentQuestionIndex = 0
            )
    }
}