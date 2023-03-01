package com.example.aacademysamples.ui.task2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.aacademysamples.R
import com.example.aacademysamples.databinding.FragmentTaskTwoBinding
import com.example.aacademysamples.viewBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TaskTwoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskTwoFragment : Fragment(R.layout.fragment_task_two) {

    val binding by viewBinding { FragmentTaskTwoBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        requireActivity().window.statusBarColor = requireContext().getColor(R.color.yellow)

        binding.apply {
            icBack.setOnClickListener {
                findNavController().navigate(R.id.action_taskTwoFragment_to_homeFragment)
            }


        }
    }


}