package com.terrabyte.ieltswritingassistant.ui.vocabularies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.terrabyte.ieltswritingassistant.R
import com.terrabyte.ieltswritingassistant.databinding.FragmentVocabListBinding
import com.terrabyte.ieltswritingassistant.viewBinding

class VocabListFragment : Fragment(R.layout.fragment_vocab_list) {

    private val binding by viewBinding { FragmentVocabListBinding.bind(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.apply {


        }
    }
}