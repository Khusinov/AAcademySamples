package com.khusinov.aacademysamples.ui.task2

import android.os.Bundle
import android.util.Log
import android.view.View
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

        val db = Firebase.firestore
        db.collection("question")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Galdi bi", "${document.id} => ${document.data}")

                    val taskTwo = TaskTwo()
                    taskTwo.body = document.data["body"].toString()
                    taskTwo.description = document.data["description"].toString()
                    taskTwo.imageUrl = document.data["imageUrl"].toString()
                    taskTwo.sample = document.data["sample"].toString()
                    taskTwo.ideas = document.data["ideas"].toString()
                    taskTwo.sort = document.data["sort"].toString().toInt()
                    taskTwo.type = document.data["type"].toString().toInt()
                    taskTwo.vocabulary = document.data["vocabulary"].toString()

                    // taskOne.date = document.data["date"]

                    taskTwoList.add(taskTwo)

                    callIt(taskTwoList)
                }
            }.addOnFailureListener { exception ->
                Log.w(TAG, "setupUI: Error getting documents.  ", exception)

            }






        requireActivity().window.statusBarColor = requireContext().getColor(R.color.yellow)

        binding.apply {
            icBack.setOnClickListener {
                findNavController().navigate(R.id.action_taskTwoFragment_to_homeFragment)
            }

        }
    }

    private fun callIt(taskTwoList: ArrayList<TaskTwo>) {
        binding.apply {
            val recyclerView = rv
            val adapter = TaskTwoAdapter(taskTwoList)
            recyclerView.adapter = adapter
            adapter.submitList(taskTwoList)
        }
    }


}