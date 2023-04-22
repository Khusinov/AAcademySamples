package com.khusinov.aacademysamples.ui.mistakes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.khusinov.aacademysamples.R
import com.khusinov.aacademysamples.databinding.FragmentMistakesBinding
import com.khusinov.aacademysamples.model.Tip
import com.khusinov.aacademysamples.viewBinding


class MistakesFragment : Fragment(R.layout.fragment_mistakes) {
    val binding by viewBinding { FragmentMistakesBinding.bind(it) }
    private val TAG = "MistakesFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {

        binding.pBar.visibility = View.VISIBLE
        binding.rv.visibility = View.GONE

        requireActivity().window.statusBarColor = requireContext().getColor(R.color.blue)

        binding.apply {

        }

        val mistakeList = ArrayList<Tip>()

        val db = Firebase.firestore
        db.collection("mistake").get().addOnSuccessListener { result ->
            for (document in result) {
                Log.d("Galdi bi", "${document.id} => ${document.data}")

                val tip = Tip(
                    document.data["tipBody"].toString(), document.data["imageUrl"].toString()
                )
                mistakeList.add(tip)
            }
            callIt(mistakeList)
        }.addOnFailureListener { exception ->
            Log.w(TAG, "setupUI: Error getting documents.  ", exception)

        }

    }

    private fun callIt(mistakeList: java.util.ArrayList<Tip>) {
        binding.apply {
            pBar.visibility = View.GONE
            rv.visibility = View.VISIBLE

            val recyclerView = rv
            val adapter = MistakesAdapter()
            recyclerView.adapter = adapter
            adapter.submitList(mistakeList)

        }
    }

}