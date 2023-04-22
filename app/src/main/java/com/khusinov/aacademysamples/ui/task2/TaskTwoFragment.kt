package com.khusinov.aacademysamples.ui.task2

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.khusinov.aacademysamples.R
import com.khusinov.aacademysamples.databinding.FragmentTaskTwoBinding
import com.khusinov.aacademysamples.model.TaskOne
import com.khusinov.aacademysamples.model.TaskTwo
import com.khusinov.aacademysamples.ui.task1.TaskOneAdapter
import com.khusinov.aacademysamples.viewBinding


class TaskTwoFragment : Fragment(R.layout.fragment_task_two) {

    val binding by viewBinding { FragmentTaskTwoBinding.bind(it) }
    private val TAG = "TaskTwoFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {

        val taskTwoList = ArrayList<TaskTwo>()
        binding.pBar.visibility = View.VISIBLE
        binding.rv.visibility = View.GONE


        val db = Firebase.firestore
        db.collection("question2")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Galdi bi", "${document.id} => ${document.data}")

                    val taskTwo = TaskTwo(
                        document.data["body"].toString(),
                        document.data["imageUrl"].toString(),
                        document.data["date"].toString(),
                        document.data["sample"].toString(),
                        document.data["question"].toString(),
                        document.data["vocabulary"].toString(),
                        document.data["ideas"].toString(),
                        document.data["author"].toString(),
                        document.data["score"].toString(),
                        document.data["sort"].toString().toInt(),
                        document.data["type"].toString().toInt(),
                        document.data["grammar"].toString(),
                    )

                    taskTwoList.add(taskTwo)

                    callIt(taskTwoList)
                }
            }.addOnFailureListener { exception ->
                Log.w(TAG, "setupUI: Error getting documents.  ", exception)

            }

        requireActivity().window.statusBarColor = requireContext().getColor(R.color.yellow)

        binding.apply {
        }
    }

    private fun callIt(taskTwoList: ArrayList<TaskTwo>) {
        binding.apply {
            pBar.visibility = View.GONE
            rv.visibility = View.VISIBLE

            val recyclerView = rv
            val adapter = TaskTwoAdapter()
            recyclerView.adapter = adapter
            adapter.submitList(taskTwoList)

            adapter.onClick = {
                findNavController().navigate(R.id.action_taskTwoFragment_to_taskTwoMoreFragment, bundleOf("task" to it))
            }
        }
    }


}