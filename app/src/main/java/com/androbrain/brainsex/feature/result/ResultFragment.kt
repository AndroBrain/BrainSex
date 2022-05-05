package com.androbrain.brainsex.feature.result

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.androbrain.brainsex.R
import com.androbrain.brainsex.core.TestResult
import com.androbrain.brainsex.databinding.FragmentResultBinding
import com.androbrain.brainsex.extension.shareContent
import com.androbrain.brainsex.navigation.nav_arguments

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    private val testResult by lazy { createTestResult() }
    private val points by lazy { arguments?.getString(nav_arguments.points)!! }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(layoutInflater)
        setupViews()
        setupActions()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        textTitle.text = testResult.title
        textDescription.text = testResult.description
        textScore.text = arguments?.getString(nav_arguments.points)

        indicatorPoints.doOnLayout {
            indicatorDotPoints.x = it.width / points.toFloat()
            it.isVisible = true
        }
    }

    private fun setupActions() = with(binding) {
        buttonShare.setOnClickListener {
            requireContext().shareContent {
                putExtra(Intent.EXTRA_TEXT, testResult.description)
                putExtra(Intent.EXTRA_TITLE, "$points\n${testResult.title}")
                type = "text/plain"
            }
        }

        buttonMenu.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun createTestResult(): TestResult {
        val points = points.toInt()
        return when {
            points > 300 -> TestResult(
                title = getString(R.string.result_more_than_300_title),
                description = getString(R.string.result_more_than_180_description)
            )
            points > 180 -> TestResult(
                title = getString(R.string.result_180_300_title),
                description = getString(R.string.result_more_than_180_description)
            )
            points > 150 -> TestResult(
                title = getString(R.string.result_150_180_title),
                description = getString(R.string.result_150_180_description),
            )
            points > 0 -> TestResult(
                title = getString(R.string.result_0_150_title),
                description = getString(R.string.result_0_150_description),
            )
            else -> TestResult(
                title = getString(R.string.result_less_than_0_title),
                description = getString(R.string.result_less_than_0_description)
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
