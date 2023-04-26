package com.khusinov.aacademysamples

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khusinov.aacademysamples.databinding.FragmentAboutUsBinding


class AboutUsFragment : Fragment(R.layout.fragment_about_us) {
    val binding by viewBinding{ FragmentAboutUsBinding.bind(it)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.apply {

        }
    }

}