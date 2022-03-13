package com.androbrain.brainsex.ui.test

import androidx.annotation.IdRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

private const val KEY_STATE = "STATE"

@HiltViewModel
class TestViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(savedStateHandle.get(KEY_STATE) ?: TestState.Initial)
    val state = _state.asStateFlow()

    fun nextQuestionClicked() {
        _state.update {
            it.copy(
                currentQuestionIndex = it.currentQuestionIndex + 1,
                selectedButtonId = null
            )
        }
    }

    fun updateSelectedButtonId(@IdRes buttonId: Int) {
        _state.update {
            it.copy(selectedButtonId = buttonId)
        }
    }

    override fun onCleared() {
        savedStateHandle[KEY_STATE] = state.value
        super.onCleared()
    }

}
