package com.khusinov.aacademysamples.ui.tips


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.khusinov.aacademysamples.R
import com.khusinov.aacademysamples.databinding.FragmentTipsBinding
import com.khusinov.aacademysamples.viewBinding


class TipsFragment : Fragment(R.layout.fragment_tips) {

    val binding by viewBinding { FragmentTipsBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        requireActivity().window.statusBarColor = requireContext().getColor(R.color.green)

        binding.apply {

            icBack.setOnClickListener {
                findNavController().navigate(R.id.action_tipsFragment_to_homeFragment)
            }

        }
    }


}