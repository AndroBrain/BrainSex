package com.androbrain.brainsex.ui.test

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.androbrain.brainsex.data.TestDataCreator
import com.androbrain.brainsex.databinding.FragmentTestBinding
import com.androbrain.brainsex.extension.animateProgressCompat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val NUMBER_OF_QUESTIONS = 30

@AndroidEntryPoint
class TestFragment : Fragment() {

    private var _binding: FragmentTestBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTestBinding.inflate(layoutInflater)
        setupViews()
        setupActions()
        setupObservers()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        progressIndicator.max = NUMBER_OF_QUESTIONS
    }

    private fun setupActions() = with(binding) {
        buttonNext.setOnClickListener { viewModel.nextQuestionClicked() }
    }

    private fun setupObservers() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    val data = TestDataCreator.getBrainSexQuestions(resources, false)
                    val answerWithQuestion = data.getOrNull(state.currentQuestionIndex)
                    buttonNext.isEnabled = state.selectedButtonId != null

                    if (answerWithQuestion == null) {
//                        TODO show end screen
                        return@collect
                    }

                    progressIndicator.animateProgressCompat(state.currentQuestionIndex + 1)

                    containerAnswers.removeAllViews()
                    answerWithQuestion.answers.forEachIndexed { index, (answerText, _) ->
                        containerAnswers.addView(RadioButton(requireContext()).apply {
                            id = index
                            if (id == state.selectedButtonId) {
                                isChecked = true
                            }
                            text = answerText
                            setOnCheckedChangeListener { _, isChecked ->
                                if (isChecked) {
                                    viewModel.updateSelectedButtonId(id)
                                }
                            }
                        })
                    }
                    textQuestion.text = answerWithQuestion.question
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
