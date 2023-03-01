package com.example.aacademysamples.ui.task1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.aacademysamples.R
import com.example.aacademysamples.databinding.FragmentTask1Binding
import com.example.aacademysamples.viewBinding


class TaskOneFragment : Fragment(R.layout.fragment_task1) {

    val binding by viewBinding { FragmentTask1Binding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        requireActivity().window.statusBarColor = requireContext().getColor(R.color.red)

        binding.apply {

            icBack.setOnClickListener {
                findNavController().navigate(R.id.action_taskOneFragment_to_homeFragment)
            }

        }
    }


}