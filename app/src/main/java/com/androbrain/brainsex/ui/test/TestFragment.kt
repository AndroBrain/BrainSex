package com.androbrain.brainsex.ui.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.core.view.doOnLayout
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.androbrain.brainsex.R
import com.androbrain.brainsex.databinding.FragmentTestBinding
import com.androbrain.brainsex.databinding.RadioButtonAnswerChoiceBinding
import com.androbrain.brainsex.extension.animateProgressCompat
import com.androbrain.brainsex.model.QuestionWithAnswers
import com.androbrain.brainsex.navigation.nav_routes
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
        setupObservers()
        viewModel.loadData()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        progressIndicator.max = NUMBER_OF_QUESTIONS
        buttonNext.setOnClickListener { viewModel.nextQuestionClicked() }
    }

    private fun setupObservers() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { (currentQuestionIndex, answerWithQuestions, selectedButtonId, points) ->

                    if (answerWithQuestions == null) {
                        findNavController().popBackStack()
                        findNavController().navigate("${nav_routes.result}/$points")
                        return@collect
                    }

                    progressIndicator.animateProgressCompat(currentQuestionIndex + 1)

                    setupAnswers(answerWithQuestions, selectedButtonId)
                    textQuestion.text = answerWithQuestions.question
                }
            }
        }
    }

    private fun setupAnswers(
        answerWithQuestion: QuestionWithAnswers,
        @IdRes selectedButtonId: Int?
    ) = with(binding) {
        containerAnswers.removeAllViews()
        answerWithQuestion.answers.forEachIndexed { index, answer ->
            containerAnswers.addView(RadioButtonAnswerChoiceBinding.inflate(layoutInflater).root.apply {
                id = index
                if (id == selectedButtonId) {
                    isChecked = true
                }
                text = answer.text
                setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        viewModel.updateSelectedButtonId(id)
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
