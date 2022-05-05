package com.androbrain.brainsex.feature.result

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.androbrain.brainsex.R
import com.androbrain.brainsex.databinding.FragmentResultBinding
import com.androbrain.brainsex.extension.shareContent
import com.androbrain.brainsex.navigation.nav_arguments
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val MAX_POSITIVE_POINTS = 450
private const val MAX_NEGATIVE_POINTS = 150

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ResultViewModel by viewModels()

    private val points by lazy { arguments?.getString(nav_arguments.points)!! }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(layoutInflater)
        setupViews()
        setupActions()
        setupObservers()
        return binding.root
    }

    private fun setupViews() = with(binding) {
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
        buttonMenu.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupObservers() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { (title, description) ->
                    textTitle.setText(title)
                    textDescription.setText(description)

                    buttonShare.setOnClickListener {
                        requireContext().shareContent {
                            putExtra(Intent.EXTRA_TITLE, getString(title))
                            putExtra(Intent.EXTRA_TEXT, "$points\n${getString(description)}")
                            type = "text/plain"
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
