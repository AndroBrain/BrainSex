package com.androbrain.brainsex.feature.test

import android.util.Log
import androidx.annotation.IdRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.androbrain.brainsex.core.gender.Gender
import com.androbrain.brainsex.data.repository.TestRepository
import com.androbrain.brainsex.navigation.nav_arguments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

private const val KEY_STATE = "STATE"

private const val NO_ANSWER_POINTS = 5

@HiltViewModel
class TestViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    testRepository: TestRepository
) : ViewModel() {
    private val _state =
        MutableStateFlow(savedStateHandle.get(KEY_STATE) ?: GenderTestState.Initial)
    val stateGender: StateFlow<GenderTestState> = _state.asStateFlow()

    val questionsWithAnswers = testRepository.getGenderTest()

    fun loadData() {
        _state.update {
            it.copy(
                gender = Gender.valueOf(savedStateHandle.get<String>(nav_arguments.gender)!!),
                answerWithQuestions = questionsWithAnswers.getOrNull(it.currentQuestionIndex)
            )
        }
    }

    fun nextQuestionClicked() {
        _state.update {
            it.copy(
                currentQuestionIndex = it.currentQuestionIndex + 1,
                answerWithQuestions = questionsWithAnswers.getOrNull(it.currentQuestionIndex + 1),
                selectedButtonId = null,
                points = it.points + getPointsByIndex(it)
            )
        }
        Log.d("TestData", _state.value.toString())
    }

    fun updateSelectedButtonId(@IdRes buttonId: Int) {
        _state.update {
            it.copy(selectedButtonId = buttonId)
        }
    }

    private fun getPointsByIndex(currentState: GenderTestState): Int {
        val buttonId = currentState.selectedButtonId ?: return NO_ANSWER_POINTS
        val gender = currentState.gender ?: return NO_ANSWER_POINTS
        return currentState.answerWithQuestions?.answers?.getOrNull(buttonId)?.points?.getPointsByFilter(
            gender
        ) ?: NO_ANSWER_POINTS
    }

    override fun onCleared() {
        savedStateHandle[KEY_STATE] = stateGender.value
        super.onCleared()
    }

}
