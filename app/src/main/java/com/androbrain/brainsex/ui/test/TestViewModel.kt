package com.androbrain.brainsex.ui.test

import androidx.annotation.IdRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.androbrain.brainsex.model.QuestionWithAnswers
import com.androbrain.brainsex.navigation.nav_arguments
import com.androbrain.brainsex.ui.choosegender.Gender
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

private const val KEY_STATE = "STATE"

private const val A_MALE_POINTS = 15
private const val A_FEMALE_POINTS = 10
private const val B_POINTS = 5
private const val C_POINTS = -5
private const val NO_ANSWER_POINTS = 5

@HiltViewModel
class TestViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val testData: List<QuestionWithAnswers>,
) : ViewModel() {
    private val _state = MutableStateFlow(savedStateHandle.get(KEY_STATE) ?: TestState.Initial)
    val state: StateFlow<TestState> = _state.asStateFlow()

    fun loadData() {
        _state.update {
            it.copy(
                gender = Gender.valueOf(savedStateHandle.get<String>(nav_arguments.gender)!!),
                answerWithQuestions = testData.getOrNull(it.currentQuestionIndex)
            )
        }
    }

    fun nextQuestionClicked() {
        _state.update {
            it.copy(
                currentQuestionIndex = it.currentQuestionIndex + 1,
                answerWithQuestions = testData.getOrNull(it.currentQuestionIndex + 1),
                selectedButtonId = null,
                points = it.points + getPointsByIndex(it)
            )
        }
    }

    fun updateSelectedButtonId(@IdRes buttonId: Int) {
        _state.update {
            it.copy(selectedButtonId = buttonId)
        }
    }

    private fun getPointsByIndex(currentState: TestState): Int =
        when (currentState.selectedButtonId) {
            0 -> when (currentState.gender) {
                Gender.MALE -> A_MALE_POINTS
                Gender.FEMALE -> A_FEMALE_POINTS
                else -> 0
            }
            1 -> B_POINTS
            2 -> C_POINTS
            else -> NO_ANSWER_POINTS
        }

    override fun onCleared() {
        savedStateHandle[KEY_STATE] = state.value
        super.onCleared()
    }

}
