package com.androbrain.brainsex.ui.choosegender

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.androbrain.brainsex.databinding.FragmentChooseGenderBinding
import com.androbrain.brainsex.navigation.Arguments
import com.androbrain.brainsex.navigation.Routes
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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
            findNavController().navigate("${Routes.test}/${Gender.MALE}")
        }

        buttonFemale.setOnClickListener {
            findNavController().navigate("${Routes.test}/${Gender.FEMALE}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}