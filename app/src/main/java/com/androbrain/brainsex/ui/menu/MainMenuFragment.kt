package com.androbrain.brainsex.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.androbrain.brainsex.databinding.FragmentMainMenuBinding
import com.androbrain.brainsex.navigation.nav_routes

class MainMenuFragment : Fragment() {

    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainMenuBinding.inflate(layoutInflater)
        setupActions()
        return binding.root
    }

    private fun setupActions() = with(binding) {
        buttonStartTest.setOnClickListener {
            findNavController().navigate(nav_routes.choose_gender)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
