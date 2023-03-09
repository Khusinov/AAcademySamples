package com.khusinov.aacademysamples.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.khusinov.aacademysamples.R
import com.khusinov.aacademysamples.databinding.FragmentHomeBinding
import com.khusinov.aacademysamples.viewBinding


class HomeFragment : Fragment(R.layout.fragment_home) {
    val binding by viewBinding { FragmentHomeBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {

        requireActivity().window.statusBarColor = requireContext().getColor(R.color.white)

        binding.task1.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_taskOneFragment)
        }
        binding.task2.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_taskTwoFragment)
        }
        binding.tips.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_tipsFragment)
        }
        binding.commonMistakes.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_mistakesFragment)
        }
    }


}