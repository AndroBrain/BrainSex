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

private const val MAX_POSITIVE_POINTS = 450
private const val MAX_NEGATIVE_POINTS = 150

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
            val part = it.width.toFloat() / (MAX_NEGATIVE_POINTS + MAX_POSITIVE_POINTS)
            val dotWidth = resources.getDimensionPixelSize(R.dimen.indicator_dot_points_size)

            var dotPosition =
                (points.toFloat() + MAX_NEGATIVE_POINTS) * part - dotWidth / 2
            dotPosition = if (dotPosition < 0) 0f else dotPosition

            dotPosition =
                if (dotPosition >= it.width - dotWidth) (it.width - dotWidth).toFloat() else dotPosition

            indicatorDotPoints.x = dotPosition
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
            points > 295 -> TestResult(
                title = getString(R.string.result_more_than_300_title),
                description = getString(R.string.result_more_than_180_description)
            )
            points > 175 -> TestResult(
                title = getString(R.string.result_180_300_title),
                description = getString(R.string.result_more_than_180_description)
            )
            points > 145 -> TestResult(
                title = getString(R.string.result_150_180_title),
                description = getString(R.string.result_150_180_description),
            )
            points > 5 -> TestResult(
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
