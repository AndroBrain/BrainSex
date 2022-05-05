package com.androbrain.brainsex.feature.choosegender

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.androbrain.brainsex.core.gender.Gender
import com.androbrain.brainsex.databinding.FragmentChooseGenderBinding
import com.androbrain.brainsex.extension.addFadeAnimations
import com.androbrain.brainsex.navigation.nav_routes

class ChooseGenderFragment : Fragment() {

    private var _binding: FragmentChooseGenderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseGenderBinding.inflate(layoutInflater)
        setupActions()
        return binding.root
    }

    private fun setupActions() = with(binding) {
        buttonMale.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(
                "${nav_routes.test}/${Gender.MALE}",
                navOptions { addFadeAnimations() })
        }

        buttonFemale.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(
                "${nav_routes.test}/${Gender.FEMALE}",
                navOptions { addFadeAnimations() })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}