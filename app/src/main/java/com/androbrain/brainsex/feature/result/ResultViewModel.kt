package com.androbrain.brainsex.feature.result

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.androbrain.brainsex.R
import com.androbrain.brainsex.navigation.nav_arguments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

private const val KEY_STATE = "STATE"

private const val MAX_FEMALE_POINTS = 295
private const val MAX_SHARED_POINTS = 175
private const val MAX_MALE_POINTS = 145
private const val MAX_VERY_MALE_POINTS = 0

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val points = savedStateHandle.get<String>(nav_arguments.points)!!.toInt()

    private val _state =
        MutableStateFlow(savedStateHandle.get(KEY_STATE) ?: ResultState.Initial)
    val state = _state.asStateFlow()

    init {
        updateTestResults()
    }

    private fun updateTestResults() {
        _state.update { state ->
            val (title, description) = when {
                points > MAX_FEMALE_POINTS -> Pair(
                    R.string.result_very_female_title,
                    R.string.result_female_description
                )
                points > MAX_SHARED_POINTS -> Pair(
                    R.string.result_female_title,
                    R.string.result_female_description
                )
                points > MAX_MALE_POINTS -> Pair(
                    R.string.result_shared_title,
                    R.string.result_shared_description
                )
                points > MAX_VERY_MALE_POINTS -> Pair(
                    R.string.result_male_title,
                    R.string.result_male_description,
                )
                else -> Pair(
                    R.string.result_very_male_title,
                    R.string.result_very_male_desccription
                )
            }
            state.copy(
                title = title,
                description = description
            )
        }
    }

    override fun onCleared() {
        savedStateHandle[KEY_STATE] = state.value
        super.onCleared()
    }

}
