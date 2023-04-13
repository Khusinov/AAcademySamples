package com.khusinov.aacademysamples.ui.task1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.khusinov.aacademysamples.R
import com.khusinov.aacademysamples.databinding.FragmentTask1Binding
import com.khusinov.aacademysamples.model.TaskOne
import com.khusinov.aacademysamples.viewBinding


class TaskOneFragment : Fragment(R.layout.fragment_task1) {

    val binding by viewBinding { FragmentTask1Binding.bind(it) }
    private val TAG = "TaskOneFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {

        val taskOneList = ArrayList<TaskOne>()

        val db = Firebase.firestore
        db.collection("question")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Galdi bi", "${document.id} => ${document.data}")

                    val taskOne = TaskOne()
                    taskOne.body = document.data["body"].toString()
                    taskOne.description = document.data["description"].toString()
                    taskOne.imageUrl = document.data["imageUrl"].toString()
                    taskOne.sample = document.data["sample"].toString()
                    taskOne.ideas = document.data["ideas"].toString()
                    taskOne.sort = document.data["sort"].toString().toInt()
                    taskOne.type = document.data["type"].toString().toInt()
                    taskOne.vocabulary = document.data["vocabulary"].toString()
                    taskOne.date = document.data["date"].toString()

                    taskOneList.add(taskOne)

                    callIt(taskOneList)
                }
            }.addOnFailureListener { exception ->
                Log.w(TAG, "setupUI: Error getting documents.  ", exception)

            }

        requireActivity().window.statusBarColor = requireContext().getColor(R.color.red)

        binding.apply {

            icBack.setOnClickListener {
                findNavController().navigate(R.id.action_taskOneFragment_to_homeFragment)
            }

            icon.setOnClickListener {
                findNavController().navigate(R.id.action_taskOneFragment_to_taskOneMoreFragment)
            }

        }
    }

    private fun callIt(taskOneList: ArrayList<TaskOne>) {
        binding.apply {
            val recyclerView = rv
            val adapter = TaskOneAdapter(taskOneList)
            recyclerView.adapter = adapter
            adapter.submitList(taskOneList)
        }

    }


}