package com.androbrain.brainsex.ui.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.androbrain.brainsex.data.TestDataCreator
import com.androbrain.brainsex.databinding.FragmentTestBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
        setupActions()
        setupObservers()
        return binding.root
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

                    if (answerWithQuestion == null) {
//                        TODO show end screen
                        return@collect
                    }

                    textQuestion.text = answerWithQuestion.question
                    answerA.text = answerWithQuestion.answers[0].text
                    answerB.text = answerWithQuestion.answers[1].text
                    answerC.text = answerWithQuestion.answers[2].text
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
