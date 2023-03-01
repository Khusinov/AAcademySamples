package com.example.aacademysamples.ui.mistakes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.aacademysamples.R
import com.example.aacademysamples.databinding.FragmentMistakesBinding
import com.example.aacademysamples.viewBinding


class MistakesFragment : Fragment(R.layout.fragment_mistakes) {
    val binding by viewBinding { FragmentMistakesBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {

        requireActivity().window.statusBarColor = requireContext().getColor(R.color.blue)

        binding.apply {

            icBack.setOnClickListener {
                findNavController().navigate(R.id.action_mistakesFragment_to_homeFragment)
            }
        }
    }

}