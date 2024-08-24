package com.terrabyte.ieltswritingassistant.ui.vocabularies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.terrabyte.ieltswritingassistant.R
import com.terrabyte.ieltswritingassistant.databinding.FragmentVocabulariesBinding
import com.terrabyte.ieltswritingassistant.model.Vocabularies
import com.terrabyte.ieltswritingassistant.ui.tips.TipsAdapter
import com.terrabyte.ieltswritingassistant.viewBinding

class VocabulariesFragment : Fragment() {

    val binding by viewBinding { FragmentVocabulariesBinding.bind(it) }
    private val TAG = "TipsFragment"
    private val adapter by lazy { VocabularyAdapter(this::onItemClick) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vocabularies, container, false)
    }

    private fun setupUI() {

        binding.pBar.visibility = View.VISIBLE
        binding.rv.visibility = View.GONE

        requireActivity().window.statusBarColor = requireContext().getColor(R.color.vocabBackground)

        binding.apply {


            val videosList = ArrayList<Vocabularies>()

            val db = Firebase.firestore
            db.collection("vocabularies").get().addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Galdi bi", "${document.id} => ${document.data}")

                    val video = Vocabularies(
                        document.data["id"].toString().toInt(),
                        document.data["topicName"].toString(),
                        document.data["type"].toString(),
                    )
                    videosList.add(video)

                }
                callIt(videosList.sortedBy { it.id })
            }.addOnFailureListener { exception ->
                Log.w(TAG, "setupUI: Error getting documents.  ", exception)
            }
        }
    }

    private fun callIt(list: List<Vocabularies>) {

        binding.apply {
            pBar.visibility = View.GONE
            rv.visibility = View.VISIBLE

            rv.adapter = adapter
            adapter.submitList(list)
        }
    }

    private fun onItemClick(vocabulary: Vocabularies) {
        Log.d(TAG, "onItemClick: $vocabulary")
        findNavController().navigate(R.id.action_vocabulariesFragment_to_vocabListFragment)
    }

}