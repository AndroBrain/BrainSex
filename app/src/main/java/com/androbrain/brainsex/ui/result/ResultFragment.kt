package com.androbrain.brainsex.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.androbrain.brainsex.databinding.FragmentResultBinding
import com.androbrain.brainsex.navigation.nav_arguments

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(layoutInflater)
        setupViews()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        textScore.text = arguments?.getString(nav_arguments.points)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
