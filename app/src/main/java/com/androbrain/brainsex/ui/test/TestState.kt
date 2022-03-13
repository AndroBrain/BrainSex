package com.androbrain.brainsex.ui.test

data class TestState(
    val currentQuestionIndex: Int,
    val selectedButtonId: Int?,
    val points: Int,
    val isMale: Boolean,
) {
    companion object {
        val Initial: TestState
            get() = TestState(
                currentQuestionIndex = 0,
                selectedButtonId = null,
                points = 0,
                isMale = true
            )
    }
}