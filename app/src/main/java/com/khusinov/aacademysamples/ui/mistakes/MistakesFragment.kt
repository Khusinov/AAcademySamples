package com.khusinov.aacademysamples.ui.mistakes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.khusinov.aacademysamples.R
import com.khusinov.aacademysamples.databinding.FragmentMistakesBinding
import com.khusinov.aacademysamples.viewBinding


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